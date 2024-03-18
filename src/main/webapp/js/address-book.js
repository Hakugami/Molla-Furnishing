$(document).ready(function () {
    let addresses = [];
    let userData; // Variable to store user data

    function getAddressData() {
        // Use AJAX to get the data
        $.ajax({
            url: 'loadProfile',
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                addresses = data.addresses;
                userData = data; // Store user data
                createAddressRows(data);
                console.log(addresses);
            },
            error: function (error) {
                console.log('Error:', error);
            }
        });
    }

    function createAddressRows(data) {
        // Check if data is defined
        if (!data || !data.addresses) {
            console.log('Error: Data is undefined or does not contain addresses.');
            return;
        }

        // Get the table body
        const tbody = document.querySelector('.dash__table-2 tbody');

        // Clear existing rows
        tbody.innerHTML = '';

        // Loop through the addresses
        data.addresses.forEach(function (address) {
            // Extract address details from address object
            const street = address.street;
            const city = address.city;
            const state = address.state;
            const country = address.country;
            const zipCode = address.zipCode;

            // Format the address
            const formattedAddress = `${zipCode}-${street}`;
            const region = `${city} ${state} - ${country}`;

            // Create a new row
            const row = document.createElement('tr');

            // Create the HTML for the row
            row.innerHTML = `
                <td>
                    <a class="delete-address address-book-edit btn--e-transparent-platinum-b-2" href="" data-id="${address.id}">Delete</a>
                </td>
                <td>${userData.name}</td> <!-- Access user data here -->
                <td>${formattedAddress}</td>
                <td>${region}</td>
                <td>(+0) ${userData.phone}</td> <!-- Access user data here -->
                <td></td>
            `;

            // Append the row to the table body
            tbody.appendChild(row);
        });
    }

    // Call the function to get the data
    getAddressData();

    // Attach event listener for delete buttons
    $(document).on('click', '.delete-address', function (e) {
        e.preventDefault();
        const addressId = $(this).data('id');

        // Remove address from the global array
        addresses = addresses.filter(address => address.id !== addressId);

        // Update the UI
        createAddressRows({ addresses: addresses });

        // Send a POST request to delete the address from the backend
        $.ajax({
            url: 'addressOperation',
            method: 'POST',
            data: {
                operation: 'remove', // Specify the operation
                addressId: addressId // Specify the address ID
            },
            success: function (response) {
                console.log('Address deleted successfully.');
            },
            error: function (error) {
                console.log('Error deleting address:', error);
            }
        });
    });
});
