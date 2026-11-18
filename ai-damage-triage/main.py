from fastapi import FastAPI
from api import router
from fastapi.staticfiles import StaticFiles
from fastapi.responses import FileResponse

app = FastAPI(
    title="AI Damage Triage",
    description="AI-powered product damage analysis and ticket routing",
)

app.include_router(router)

app.mount(
    "/static",
    StaticFiles(directory="frontend"),
    name="static"
)

@app.get("/")
async def home():
    return FileResponse("frontend/index.html")