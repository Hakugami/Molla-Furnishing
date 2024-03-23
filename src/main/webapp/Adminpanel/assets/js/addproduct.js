// Function to handle image preview
function previewImages(event) {
    var input = event.target;
    var smallPreviewsContainer = document.getElementById('smallImagePreviews');

    var files = input.files;
    if (files) {
        for (var i = 0; i < files.length; i++) {
            var reader = new FileReader();

            reader.onload = function (e) {
                var image = document.createElement('img');
                var preview = document.createElement('div');
                image.src = e.target.result;
                image.classList.add('small-image-preview');
                preview.classList.add('preview-container');

                // Add event listener to display image in big preview on click
                image.addEventListener('click', function() {
                    document.getElementById('bigImagePreview').src = this.src;
                });

                preview.appendChild(image);

                // Add close button to remove image from small previews
                var closeButton = document.createElement('button');
                closeButton.innerHTML = '&times;';
                closeButton.classList.add('close-button');
                closeButton.addEventListener('click', function() {
                    this.parentNode.remove();
                });
                preview.appendChild(closeButton);

                smallPreviewsContainer.appendChild(preview);
            }

            reader.readAsDataURL(files[i]);
        }
    }
}

// Attach event listener to image input
document.getElementById('imageInput').addEventListener('change', previewImages);


    const subcategoryOptions = {
        chairs: ["Armchairs", "Sofas", "Benches", "Stools"],
        tables: ["Dining Tables", "Coffee Tables", "Side Tables", "Bedside Tables"],
        storage: ["Wardrobes", "Cabinets", "Dressers", "Bookcases", "Shelving Units"],
        beds: ["Bed Frames", "Mattresses", "Nightstands", "Bunk Beds"],
        desks: ["Writing Desks", "Computer Desks", "Office Chairs", "Filing Cabinets"],
        dining: ["Dining Sets", "Buffets"],
        children: ["Cribs", "Kid Beds", "Study Desks"]
    };

const categorySelect = document.getElementById('categorySelect');
const subcategorySelect = document.getElementById('subcategorySelect');

categorySelect.addEventListener('change', function () {
    const selectedCategory = this.value;
    subcategorySelect.innerHTML = ''; // Clear previous options

    subcategoryOptions[selectedCategory].forEach(function (subcategory) {
        const option = document.createElement('option');
        option.textContent = subcategory;
        subcategorySelect.appendChild(option);
    });
});