$(document).ready(function () {

    function getAddressData() {
        // Use AJAX to get the data
        $.ajax({
            url: 'loadProfile',
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                createAddressRows(data);
                console.log(data);
            },
            error: function (error) {
                console.log('Error:', error);
            }
        });
    }

    function createAddressRows(data) {
        // Get the table body
        const tbody = document.querySelector('.dash__table-2 tbody');

        // Loop through the addresses
        data.addresses.forEach(function(addressString) {
            // Extract address components from the string
            const matches = addressString.match(/([a-zA-Z]+)=([^,]+)/g);
            let addressObj = {};
            matches.forEach(match => {
                const [key, value] = match.split('=');
                addressObj[key] = value;
            });

            // Extract address details from AddressDto object
            const street = addressObj.street;
            const city = addressObj.city;
            const state = addressObj.state;
            const country = addressObj.country;
            let zipCode = addressObj.zipCode;
            //zip code is like this 12345) so we need to remove the last character
             zipCode = zipCode.substring(0, zipCode.length - 1);
            // Create a new row
            const row = document.createElement('tr');

            // Format the address
            const formattedAddress = `${zipCode}-${street}`;
            const region = `${city}, ${state}, ${country}`;

            // Create the HTML for the row
            row.innerHTML = `
            <td>
                <a class="address-book-edit btn--e-transparent-platinum-b-2" href="delete">Delete</a>
            </td>
            <td>${data.name}</td>
            <td>${formattedAddress}</td>
            <td>${region}</td>
            <td>(+0) ${data.phone}</td>
            <td></td>
        `;

            // Append the row to the table body
            tbody.appendChild(row);
        });
    }



    // Call the function to get the data
    getAddressData();

});
