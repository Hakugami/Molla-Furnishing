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


    // Function to enable or disable all fieldsets
    function toggleFieldsets(disabled) {
        var fieldsets = document.querySelectorAll('fieldset');
        fieldsets.forEach(function(fieldset) {
            fieldset.disabled = disabled;
        });
    }

    // Function to reset form data
    function resetFormData() {
        // Reset product name
        document.getElementById('productName2').value = "Hamada Chair";

        // Reset category select
        document.getElementById('categorySelect').selectedIndex = 0;

        // Reset price
        document.getElementById('priceInput').value = "200$";

        // Reset stock
        document.getElementById('stockInput').value = "400";

        // Reset description
        document.getElementById('descriptionInput').value = "This is a hamada chair- The most Hamada chair ever";
    }

    // Function to handle edit product button click
    function editProduct() {
        // Enable all fieldsets
        toggleFieldsets(false);

        // Disable the edit product button
        document.getElementById('editProductButton').disabled = true;
    }

    // Function to handle reset button click
    function resetForm() {
        // Reset form data
        resetFormData();

        // Disable all fieldsets
        toggleFieldsets(true);

        // Enable the edit product button
        document.getElementById('editProductButton').disabled = false;
    }

    // Attach event listeners to edit product and reset buttons
    document.getElementById('editProductButton').addEventListener('click', editProduct);
    document.getElementById('resetButton').addEventListener('click', resetForm);


    //--------------------------------------------------------Ajax
