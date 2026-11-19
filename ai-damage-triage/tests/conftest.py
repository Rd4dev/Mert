import pytest
from pages.triage_page import TriagePage

@pytest.fixture
def triage_page(page):
    return TriagePage(page)