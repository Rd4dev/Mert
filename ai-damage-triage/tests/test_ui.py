from playwright.sync_api import Page, expect
from pages.triage_page import TriagePage

def test_homepage_loads(page: Page):
    triage_page = TriagePage(page)
    triage_page.open()

    expect(page).to_have_title("AI Damage Triage")

def test_image_upload_preview(page: Page):
    triage_page = TriagePage(page)
    triage_page.open()

    triage_page.upload_image("images/damaged-product1.png")
    triage_page.expect_image_preview("damaged-product1.png")

def test_analysis_displays_ticket(page: Page):
    page.goto("http://localhost:8000")

    page.route(
        "**/analyze",
        lambda route: route.fulfill(
            status=200,
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

def test_analysis_server_error(page: Page):
    page.goto("http://localhost:8000")

    page.route(
        "**/analyze",
        lambda route: route.fulfill(
            status=500,
            content_type="application/json",
            json={"detail": "Internal Server Error"}
        )
    )

    page.locator("#imageInput").set_input_files(
        "images/damaged-product1.png"
    )

    page.locator("#analyzeButton").click()

    # Verify the actual error message
    expect(page.locator("#statusMessage")).to_have_text(
        "Something went wrong while analyzing the image."
    )

    # Verify loading indicator disappears
    expect(page.locator("#loadingSection")).to_be_hidden()

    # Verify result section is not displayed
    expect(page.locator("#resultSection")).to_be_hidden()

def test_analysis_network_failure(page: Page):
    page.goto("http://localhost:8000")

    page.route(
        "**/analyze",
        lambda route: route.abort()
    )

    page.locator("#imageInput").set_input_files(
        "images/damaged-product1.png"
    )
    page.locator("#analyzeButton").click()

    expect(page.locator("#statusMessage")).to_have_text(
        "Something went wrong while analyzing the image."
    )
    expect(page.locator("#loadingSection")).to_be_hidden()
    expect(page.locator("#resultSection")).to_be_hidden()

