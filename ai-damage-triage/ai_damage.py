import ollama
import json
import uuid

from rag import (
    create_vector_store,
    retrieve_policy,
    build_policy_context
)

vector_store = create_vector_store()

def analyze_damage(image_path):
    content = """
    Analyze this damaged product image.
    
    Return ONLY valid JSON. Do not include markdown,
    explanations, or any text outside the JSON.
    
    Use exactly these fields:
    {
        "product_type": "...",
        "damage_type": "...",
        "description": "...",
        "severity": "LOW | MEDIUM | HIGH"
    }
    
    Rules:
    - severity must be exactly LOW, MEDIUM, or HIGH.
    - damage_type must be one of:
        - screen_damage
        - battery_damage
        - water_damage
        - packaging_damage
        - cosmetic_damage
        - breakage
        - other
    """

    response = ollama.chat(
        model="gemma3:4b",
        messages=[
            {
                "role": "user",
                "content": content,
                "images": [image_path]
            }
        ]
    )

    content = response["message"]["content"]
    content = (
        content
        .replace("```json", "")
        .replace("```", "")
        .strip()
    )

    data = json.loads(content)

    # RAG
    policies = retrieve_policy(
        vector_store,
        data["damage_type"]
    )

    policy_context = build_policy_context(policies)

    # Second LLM
    final_prompt = f"""
    You are a product damage triage system.

    Initial image analysis:
    {data}

    Relevant company policy:
    {policy_context}

    Use the company policy as the source of truth.

    Determine:
    1. Final severity
    2. Assigned team
    3. Reason

    Return ONLY valid JSON.

    {{
        "severity": "LOW | MEDIUM | HIGH",
        "assigned_team": "...",
        "reason": "..."
    }}
    """

    final_response = ollama.chat(
        model="gemma3:4b",
        messages=[
            {
                "role": "user",
                "content": final_prompt
            }
        ]
    )

    final_content = final_response["message"]["content"]

    final_content = (
        final_content
        .replace("```json", "")
        .replace("```", "")
        .strip()
    )

    final_data = json.loads(final_content)

    # Create ticket
    ticket = {
        "ticket_id": f"DAM-{str(uuid.uuid4())[:8].upper()}",
        "product": data["product_type"],
        "issue": data["damage_type"],
        "description": data["description"],
        "severity": final_data["severity"],
        "assigned_team": final_data["assigned_team"],
        "reason": final_data["reason"]
    }

    return ticket