document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('addAddress').addEventListener('click', function () {
        // Clone the address fields row
        var addressRow = document.querySelector('.address-row').cloneNode(true);

        // Clear the values of cloned input fields
        var inputs = addressRow.querySelectorAll('input[type="text"]');
        inputs.forEach(function (input) {
            input.value = '';
        });

        // Append the cloned address fields row to the form
        document.querySelector('.address-fields').appendChild(addressRow);
    });




});

// Function to remove an address row
function removeAddressRow() {
    // Get the parent address row
    let addressRow = this.closest('.address-row');

    // Remove the address row
    addressRow.remove();
}

// Add event listener for all existing "Remove" buttons
document.querySelectorAll('.remove-address-btn').forEach(button => {
    button.addEventListener('click', removeAddressRow);
});

// Event listener for dynamically added "Remove" buttons
document.getElementById('addAddress').addEventListener('click', function() {
    // Your code to add a new address field here

    // After adding, attach event listener to new "Remove" button
    let removeButtons = document.querySelectorAll('.remove-address-btn');
    removeButtons[removeButtons.length - 1].addEventListener('click', removeAddressRow);
});


//Image Preview
function previewImage(event) {
    var input = event.target;
    var preview = document.getElementById('imagePreview');

    while (preview.firstChild) {
        preview.removeChild(preview.firstChild); // Clear existing preview
    }

    var files = input.files;
    if (files && files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            var image = document.createElement('img');
            image.src = e.target.result;
            image.classList.add('img-thumbnail'); // Add Bootstrap img-thumbnail class for a tiny preview
            preview.appendChild(image);
        }

        reader.readAsDataURL(files[0]); // Read the image file as a data URL
    }
}