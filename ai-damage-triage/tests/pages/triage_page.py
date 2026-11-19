from playwright.sync_api import Page, expect

class TriagePage:
    def __init__(self, page: Page):
        self.page = page

        self.image_input = page.locator("#imageInput")
        self.selected_file = page.locator("#selectedFile")
        self.preview_container = page.locator("#previewContainer")
        self.image_preview = page.locator("#imagePreview")
        self.analyze_button = page.locator("#analyzeButton")
        self.result_section = page.locator("#resultSection")
        self.ticket_id = page.locator("#ticketId")
        self.status_message = page.locator("#statusMessage")
        self.loading_section = page.locator("#loadingSection")

    def open(self):
        self.page.goto("http://localhost:8000")

    def upload_image(self, image_path: str):
        self.page.locator("#imageInput").set_input_files(image_path)

    def analyze(self):
        self.page.locator("#analyzeButton").click()

    def expect_image_preview(self, filename: str):
        expect(self.selected_file).to_contain_text(filename)
        expect(self.preview_container).to_be_visible()
        expect(self.image_preview).to_be_visible()

    def expect_ticket_displayed(self, ticket_id: str):
        expect(self.result_section).to_be_visible()
        expect(self.ticket_id).to_have_text(ticket_id)

    def expect_error_message(self, message: str):
        expect(self.status_message).to_have_text(message)