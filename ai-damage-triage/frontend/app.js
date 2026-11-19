const imageInput = document.getElementById("imageInput");
const chooseImageButton = document.getElementById("chooseImageButton");
const analyzeButton = document.getElementById("analyzeButton");

const uploadArea = document.getElementById("uploadArea");
const selectedFile = document.getElementById("selectedFile");

const previewContainer = document.getElementById("previewContainer");
const imagePreview = document.getElementById("imagePreview");

const statusMessage = document.getElementById("statusMessage");

const loadingSection = document.getElementById("loadingSection");
const resultSection = document.getElementById("resultSection");

const ticketId = document.getElementById("ticketId");
const severityBadge = document.getElementById("severityBadge");

const product = document.getElementById("product");
const issue = document.getElementById("issue");
const assignedTeam = document.getElementById("assignedTeam");

const description = document.getElementById("description");
const reason = document.getElementById("reason");

const analyzeAnotherButton =
    document.getElementById("analyzeAnotherButton");

let selectedImage = null;


chooseImageButton.addEventListener("click", () => {
    imageInput.click();
});


imageInput.addEventListener("change", () => {

    const file = imageInput.files[0];

    if (!file) {
        return;
    }

    handleSelectedFile(file);
});

function handleSelectedFile(file) {

    const allowedTypes = [
        "image/png",
        "image/jpeg"
    ];

    if (!allowedTypes.includes(file.type)) {

        showError(
            "Please select a PNG or JPEG image."
        );

        imageInput.value = "";
        return;
    }

    selectedImage = file;

    selectedFile.textContent =
        `Selected: ${file.name}`;

    analyzeButton.disabled = false;

    statusMessage.textContent = "";

    const imageUrl = URL.createObjectURL(file);

    imagePreview.src = imageUrl;

    previewContainer.classList.remove("hidden");
}

uploadArea.addEventListener("dragover", (event) => {

    event.preventDefault();

    uploadArea.classList.add("drag-over");
});


uploadArea.addEventListener("dragleave", () => {

    uploadArea.classList.remove("drag-over");
});


uploadArea.addEventListener("drop", (event) => {

    event.preventDefault();

    uploadArea.classList.remove("drag-over");

    const file = event.dataTransfer.files[0];

    if (!file) {
        return;
    }

    handleSelectedFile(file);
});

analyzeButton.addEventListener("click", async () => {

    if (!selectedImage) {
        showError("Please select an image first.");
        return;
    }

    await analyzeImage();
});

async function analyzeImage() {

    try {
        resultSection.classList.add("hidden");
        loadingSection.classList.remove("hidden");

        analyzeButton.disabled = true;

        statusMessage.textContent = "";

        const formData = new FormData();

        formData.append("image", selectedImage);

        const response = await fetch("/analyze", {
            method: "POST",
            body: formData
        });

        if (!response.ok) {

            throw new Error(
                `Server returned ${response.status}`
            );
        }

        const ticket = await response.json();

        displayTicket(ticket);

    } catch (error) {

        console.error(error);

        showError(
            "Something went wrong while analyzing the image."
        );

    } finally {

        loadingSection.classList.add("hidden");

        analyzeButton.disabled = false;
    }
}

function displayTicket(ticket) {

    ticketId.textContent =
        ticket.ticket_id;

    product.textContent =
        ticket.product;

    issue.textContent =
        ticket.issue;

    assignedTeam.textContent =
        ticket.assigned_team;

    description.textContent =
        ticket.description;

    reason.textContent =
        ticket.reason;

    setSeverity(ticket.severity);

    resultSection.classList.remove("hidden");

    resultSection.scrollIntoView({
        behavior: "smooth"
    });
}

function setSeverity(severity) {

    severityBadge.textContent = severity;

    severityBadge.classList.remove(
        "severity-low",
        "severity-medium",
        "severity-high"
    );

    const normalizedSeverity =
        String(severity).toLowerCase();

    if (normalizedSeverity === "low") {

        severityBadge.classList.add(
            "severity-low"
        );

    } else if (normalizedSeverity === "medium") {

        severityBadge.classList.add(
            "severity-medium"
        );

    } else if (normalizedSeverity === "high") {

        severityBadge.classList.add(
            "severity-high"
        );
    }
}

function showError(message) {

    statusMessage.textContent = message;
}

analyzeAnotherButton.addEventListener("click", () => {

    selectedImage = null;

    imageInput.value = "";

    selectedFile.textContent =
        "No image selected";

    analyzeButton.disabled = true;

    previewContainer.classList.add("hidden");

    resultSection.classList.add("hidden");

    statusMessage.textContent = "";

    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
});
