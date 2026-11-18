from playwright.sync_api import Page, expect

def test_homepage_loads(page: Page):
    page.goto("http://localhost:8000")

    expect(page).to_have_title("AI Damage Triage")

def test_image_upload_preview(page: Page):
    page.goto("http://localhost:8000")

    page.locator("#imageInput").set_input_files(
        "images/damaged-product1.png"
    )

    expect(page.locator("#selectedFile")).to_contain_text(
        "damaged-product1.png"
    )

    expect(page.locator("#previewContainer")).to_be_visible()

    expect(page.locator("#imagePreview")).to_be_visible()

def test_analysis_displays_ticket(page: Page):
    page.goto("http://localhost:8000")

    page.route(
        "**/analyze",
        lambda route: route.fulfill(
            status =200,
            content_type="application/json",
            json={
                "ticket_id": "TICKET-001",
                "product": "Laptop",
                "issue": "Cracked screen",
                "description": "Physical damage to display",
                "severity": "HIGH",
                "assigned_team": "Hardware Repair",
                "reason": "Screen damage requires repair"
            }
        )
    )

    # Upload an image
    page.locator("#imageInput").set_input_files(
        "images/damaged-product1.png"
    )

    # Click Analyze
    page.locator("#analyzeButton").click()

    # Verify generated ticket
    expect(page.locator("#resultSection")).to_be_visible()

    expect(page.locator("#ticketId")).to_have_text("TICKET-001")
    expect(page.locator("#product")).to_have_text("Laptop")
    expect(page.locator("#severityBadge")).to_contain_text("HIGH")
    expect(page.locator("#assignedTeam")).to_have_text("Hardware Repair")

