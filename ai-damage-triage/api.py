from fastapi import FastAPI, UploadFile, File
from pathlib import Path

from ai_damage import analyze_damage

app = FastAPI(
    title="AI Damage Triage",
    description="AI-powered product damage analysis and ticket routing",
)

UPLOAD_DIR = Path("uploads")
UPLOAD_DIR.mkdir(exist_ok=True)

@app.post("/analyze")
async def analyze_image(image: UploadFile = File(...)):
    file_path = UPLOAD_DIR / image.filename

    contents = await image.read()

    with open(file_path, "wb") as f:
        f.write(contents)

    ticket = analyze_damage(str(file_path))
    return ticket