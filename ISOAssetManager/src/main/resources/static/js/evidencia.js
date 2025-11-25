// Manejo de drag & drop y preview del archivo
const fileInput = document.getElementById('file-input');
const fileInputLabel = document.querySelector('.file-input-label');
const fileSelected = document.getElementById('file-selected');
const fileName = document.getElementById('file-name');

// Cuando se selecciona un archivo
if (fileInput) {
    fileInput.addEventListener('change', function(e) {
        if (this.files && this.files[0]) {
            const file = this.files[0];
            const size = (file.size / 1024 / 1024).toFixed(2);
            fileName.textContent = file.name + ' (' + size + ' MB)';
            fileInputLabel.style.display = 'none';
            fileSelected.style.display = 'flex';
        }
    });
}

// Drag & drop
if (fileInputLabel) {
    ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
        fileInputLabel.addEventListener(eventName, preventDefaults, false);
    });

    function preventDefaults(e) {
        e.preventDefault();
        e.stopPropagation();
    }

    ['dragenter', 'dragover'].forEach(eventName => {
        fileInputLabel.addEventListener(eventName, () => {
            fileInputLabel.classList.add('drag-over');
        });
    });

    ['dragleave', 'drop'].forEach(eventName => {
        fileInputLabel.addEventListener(eventName, () => {
            fileInputLabel.classList.remove('drag-over');
        });
    });

    fileInputLabel.addEventListener('drop', (e) => {
        const dt = e.dataTransfer;
        const files = dt.files;
        fileInput.files = files;

        const event = new Event('change', { bubbles: true });
        fileInput.dispatchEvent(event);
    });
}

// Mostrar el formulario principal de upload cuando sea visible
document.addEventListener('DOMContentLoaded', () => {
    const uploadForm = document.getElementById('upload-form');
    if (uploadForm) {
        uploadForm.addEventListener('submit', (e) => {
            const submitBtn = uploadForm.querySelector('button[type="submit"]');
            submitBtn.disabled = true;
            submitBtn.textContent = '⏳ Subiendo...';
        });
    }
});