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

                // Add event listener to display image in big preview on click
                image.on('click', function() {
                    $('#bigImagePreview').attr('src', $(this).attr('src'));
                });

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

// Function to handle displaying images when fetching product details
function displayProductImages(product) {
    if (product.images && product.images.length > 0) {
        $('#bigImagePreview').attr('src', product.images[0]);
        $('#smallImagePreviews').empty(); // Clear existing previews
        for (var i = 0; i < product.images.length; i++) {
            var imageSrc = product.images[i];
            var image = $('<img>').attr('src', imageSrc).addClass('small-image-preview');
            image.on('click', function() {
                $('#bigImagePreview').attr('src', $(this).attr('src'));
            });
            $('#smallImagePreviews').append(image);
        }
    }
}


// Attach event listener to image input
$('#imageInput').on('change', previewImages);

// Function to enable or disable all fieldsets
function toggleFieldsets(disabled) {
    $('fieldset').prop('disabled', disabled);
}

// Function to reset form data
function resetFormData() {
    // Reset product name
    $('#productName2').val("Hamada Chair");

    // Reset category select
    $('#categorySelect').prop('selectedIndex', 0);

    // Reset price
    $('#priceInput').val("200$");

    // Reset stock
    $('#stockInput').val("400");

    // Reset description
    $('#descriptionInput').val("This is a hamada chair- The most Hamada chair ever");
}

// Function to handle edit product button click
function editProduct() {
    // Enable all fieldsets
    toggleFieldsets(false);

    // Disable the edit product button
    $('#editProductButton').prop('disabled', true);
}

// Function to handle reset button click
function resetForm() {
    // Reset form data
    resetFormData();

    // Disable all fieldsets
    toggleFieldsets(true);

    // Enable the edit product button
    $('#editProductButton').prop('disabled', false);
}

// Attach event listeners to edit product and reset buttons
$('#editProductButton').on('click', editProduct);
$('#resetButton').on('click', resetForm);


    //--------------------------------------------------------Ajax
const subcategoryOptions = {
    "Chairs": ["Armchairs", "Sofas", "Benches", "Stools"],
    "Tables": ["Dining Tables", "Coffee Tables", "Side Tables", "Bedside Tables"],
    "Storage": ["Wardrobes", "Cabinets", "Dressers", "Bookcases", "Shelving Units"],
    "Beds": ["Bed Frames", "Mattresses", "Nightstands", "Bunk Beds"],
    "Desks & Workstations": ["Writing Desks", "Computer Desks", "Office Chairs", "Filing Cabinets"],
    "Dining Room": ["Dining Sets", "Buffets"],
    "Children's Furniture": ["Cribs", "Kid Beds", "Study Desks"]
};


$(document).ready(function() {
    var originalProductDetails;

    function getProductDetails(productId) {
        $.ajax({
            url: '/molla/view/RetrieveProductById',
            type: 'GET',
            dataType: 'json',
            data: { productId: productId },
            success: function(product) {
                originalProductDetails = JSON.parse(JSON.stringify(product));

                $('#productName2').val(product.name);
                $('#descriptionInput').val(product.description);
                $('#priceInput').val(product.price);
                $('#stockInput').val(product.quantity);
                $('#productId').text(product.productId);
                $('#rating').text(product.rating);
                $('#dateAdded').text(product.dateAdded);

                const selectedCategory = product.categoryName;
                populateSubcategories(selectedCategory);

                if (product.productDetails) {
                    $('#materialInput').val(product.productDetails.material);
                    $('#dimensionsInput').val(product.productDetails.dimensions);
                    $('#colorInput').val(product.productDetails.color);
                    $('#weightInput').val(product.productDetails.weight);
                    if (product.productDetails.subCategoryName) {
                        $('#subcategorySelect').val(product.productDetails.subCategoryName);
                    }
                }

                displayProductImages(product); // Call the function to display images

//                if (product.images && product.images.length > 0) {
//                    $('#bigImagePreview').attr('src', product.images[0]);
//                    for (var i = 0; i < product.images.length; i++) {
//                        var imageSrc = product.images[i];
//                        var image = $('<img>').attr('src', imageSrc).addClass('small-image-preview');
//                        image.on('click', function() {
//                            $('#bigImagePreview').attr('src', $(this).attr('src'));
//                        });
//                        $('#smallImagePreviews').append(image);
//                    }
//                }
            },
            error: function(xhr, status, error) {
                console.error('Error fetching product data:', error);
            }
        });
    }

    function populateSubcategories(category) {
        const subcategorySelect = $('#subcategorySelect');
        subcategorySelect.empty();

        if (subcategoryOptions[category]) {
            subcategoryOptions[category].forEach(function (subcategory) {
                const option = $('<option>').text(subcategory);
                subcategorySelect.append(option);
            });
        } else {
            console.error('No subcategories found for category:', category);
        }
    }

    $('#categorySelect').change(function() {
        const selectedCategory = $(this).val();
        populateSubcategories(selectedCategory);
    });

    const defaultCategory = $('#categorySelect').val();
    populateSubcategories(defaultCategory);

    $('#resetButton').click(function() {
        $('#productName2').val(originalProductDetails.name);
        $('#descriptionInput').val(originalProductDetails.description);
        $('#priceInput').val(originalProductDetails.price);
        $('#stockInput').val(originalProductDetails.quantity);
        if (originalProductDetails.productDetails && originalProductDetails.productDetails.subCategoryName) {
            $('#subcategorySelect').val(originalProductDetails.productDetails.subCategoryName);
        } else {
            $('#subcategorySelect').val('');
        }
        $('#materialInput').val(originalProductDetails.productDetails.material);
        $('#dimensionsInput').val(originalProductDetails.productDetails.dimensions);
        $('#colorInput').val(originalProductDetails.productDetails.color);
        $('#weightInput').val(originalProductDetails.productDetails.weight);

        $('fieldset').prop('disabled', true);
        $('#editProductButton').prop('disabled', false);
    });

$('#productForm').submit(function(event) {
    // Prevent default form submission
    event.preventDefault();
        var editedProduct = {
            productId: originalProductDetails.productId,
            name: $('#productName2').val(),
            description: $('#descriptionInput').val(),
            dateAdded: $('#dateAdded').val(),
            price: $('#priceInput').val(),
            quantity: $('#stockInput').val(),
            categoryName: $('#categorySelect').val(),
            subCategoryName: $('#subcategorySelect').val(),
            productDetails: {
                material: $('#materialInput').val(),
                dimensions: $('#dimensionsInput').val(),
                color: $('#colorInput').val(),
                weight: $('#weightInput').val()
            }
        };

        $.ajax({
            url: '/molla/view/admin/updateproduct',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(editedProduct),
            success: function(response) {
                console.log('Product edited successfully:', response);
            },
            error: function(xhr, status, error) {
                console.error('Error editing product:', error);
            }
        });
    });

    let params = new URLSearchParams(window.location.search);
    let productId = params.get('productId');

    if (productId) {
        getProductDetails(productId);
    } else {
        console.error('Product ID not found in URL.');
    }

    $('#deleteProductButton').click(function() {
        var productId = $('#productId').text();
        var confirmDelete = confirm("Are you sure you want to delete this product?");
        if (confirmDelete) {
            $.ajax({
                url: '/molla/deleteProductServlet',
                type: 'POST',
                data: { productId: productId },
                success: function(response) {
                    console.log('Product deleted successfully:', response);
                },
                error: function(xhr, status, error) {
                    console.error('Error deleting product:', error);
                }
            });
        }
    });

});






