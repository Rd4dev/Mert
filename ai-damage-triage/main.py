from ai_damage import analyze_damage

ticket = analyze_damage(
    "images/damaged-product1.png"
)

print("\n--- DAMAGE TICKET ---")
print(ticket)