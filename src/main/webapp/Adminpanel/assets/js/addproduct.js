$(document).ready(function() {
    // Function to handle image preview
    function previewImages(event) {
        var input = event.target;
        var smallPreviewsContainer = $('#smallImagePreviews');

        var files = input.files;
        if (files) {
            for (var i = 0; i < files.length; i++) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    var image = $('<img>').attr('src', e.target.result).addClass('small-image-preview');
                    var preview = $('<div>').addClass('preview-container').append(image);

                    // Add close button to remove image from small previews
                    var closeButton = $('<button>').html('&times;').addClass('close-button').on('click', function() {
                        $(this).parent().remove();
                    });
                    preview.append(closeButton);

                    smallPreviewsContainer.append(preview);
                }

                reader.readAsDataURL(files[i]);
            }
        }
    }

    // Attach event listener to image input
    $('#imageInput').on('change', previewImages);

    // Handle form submission
    $('form').on('submit', function(event) {
        showLoader();
        event.preventDefault(); // Prevent default form submission

        // Create FormData object to handle multipart form data
        var formData = new FormData(this);

        // Append other form fields to FormData
        formData.append('productName', $('#productName2').val());
        formData.append('category', $('#categorySelect').val());
        formData.append('subcategory', $('#subcategorySelect').val());
        formData.append('price', $('#priceInput').val());
        formData.append('stock', $('#stockInput').val());
        formData.append('description', $('#descriptionInput').val());
        formData.append('material', $('#materialInput').val());
        formData.append('dimensions', $('#dimensionsInput').val());
        formData.append('color', $('#colorInput').val());
        formData.append('weight', $('#weightInput').val());

        // Append each image file to FormData
        $('.small-image-preview').each(function(index, element) {
            var file = $(element).attr('src');
            formData.append('image' + index, file);
        });

        // Perform AJAX request
        $.ajax({
            url: '/view/admin/addproduct',
            type: 'POST',
            data: formData,
            contentType: false, // Let jQuery handle the contentType
            processData: false, // Prevent jQuery from processing the data
            success: function(response) {
                hideLoader();
                // Handle success response
                console.log('Product added successfully:', response);
                showNotification("Product Added successfully","Success");
            },
            error: function(xhr, status, error) {
                // Handle error response
                hideLoader();
                console.error('Error adding product:', error);
                showNotification("Product Added successfully","Success");
            }
        });
    });

        // Populate subcategories based on selected category
        const subcategoryOptions = {
            Chairs: ["Armchairs", "Sofas", "Benches", "Stools"],
            Tables: ["Dining Tables", "Coffee Tables", "Side Tables", "Bedside Tables"],
            Storage: ["Wardrobes", "Cabinets", "Dressers", "Bookcases", "Shelving Units"],
            Beds: ["Bed Frames", "Mattresses", "Nightstands", "Bunk Beds"],
            Desks: ["Writing Desks", "Computer Desks", "Office Chairs", "Filing Cabinets"],
            Dining: ["Dining Sets", "Buffets"],
            Children: ["Cribs", "Kid Beds", "Study Desks"]
        };

        const categorySelect = $('#categorySelect');
        const subcategorySelect = $('#subcategorySelect');

        categorySelect.on('change', function () {
            const selectedCategory = $(this).val();
            subcategorySelect.empty();

            subcategoryOptions[selectedCategory].forEach(function (subcategory) {
                const option = $('<option>').text(subcategory);
                subcategorySelect.append(option);
            });
        });
    });