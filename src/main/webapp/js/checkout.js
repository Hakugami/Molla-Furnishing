$(document).ready(function () {
    let addresses = [];
    let userData; // Variable to store user data
    let currentSelectedAddressId = null;

    function getAddressData() {
        // Use AJAX to get the data
        $.ajax({
            url: 'loadProfile',
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                addresses = data.addresses;
                userData = data; // Store user data
                populateShippingAddresses(addresses);
                currentSelectedAddressId = addresses[0].id;
                updateSummaryTotals(userData.creditLimit);
                console.log(addresses);
                console.log(userData);
                console.log(userData.creditLimit);
                console.log(currentSelectedAddressId);
            },
            error: function (error) {
                console.log('Error:', error);
            }
        });
    }

    $('.checkout-f__delivery').on('submit', function () {
        event.preventDefault();

        // Set operation value
        const operation = 'add';
        $(this).append($('<input>').attr({
            type: 'hidden',
            name: 'operation',
            value: operation
        }));

        const formData = $(this).serialize();

        $.ajax({
            type: 'POST',
            url: 'addressOperation',
            data: formData,
            success: function(response) {
                // handle success
                console.log(response);
            },
            error: function(error) {
                // handle error
                console.error(error);
            }
        });

    });


    function calculateSubtotal() {
        let subtotal = 0;
        $('.o-summary__item-wrap .o-card').each(function () {
            const price = parseFloat($(this).find('.o-card__price').text().replace('$', ''));
            const quantity = parseInt($(this).find('.o-card__quantity').text().replace('Quantity x ', ''));
            subtotal += price * quantity;
        });
        return subtotal;
    }
    function updateSummaryTotals(credit) {
        const subtotal = calculateSubtotal();

        // Parse credit as float
        const parsedCredit = parseFloat(credit);

        // If credit is not a number, set it to 0
        const actualCredit = isNaN(parsedCredit) ? 0 : parsedCredit;

        // Get total price from local storage
        const totalPriceFromLocalStorage = parseFloat(sessionStorage.getItem('total'));



        // Update HTML with the calculated totals
        $('#subtotal').text('$' + subtotal.toFixed(2));
        $('#credit-before').text('$' + actualCredit.toFixed(2));
        $('#credit-after').text('$' + (actualCredit - totalPriceFromLocalStorage).toFixed(2));
    }





    function populateShippingAddresses(addresses) {
        // Get the container for shipping addresses
        const addressContainer = $('.ship-b__addresses');

        console.log('Addresses:', addresses);
        // Clear existing addresses
        addressContainer.empty();

        // Loop through addresses and populate the container
        let address=addresses[0];
            // Create HTML for each address
            const addressHtml = `
                <div class="ship-b__box u-s-m-b-10">
                    <p class="ship-b__p">${address.street}, ${address.city} ${address.state}-${address.zipCode} ${address.country} (+0) ${userData.phone}</p>
                    <a class="ship-b__edit btn--e-transparent-platinum-b-2" data-modal="modal" data-modal-id="#edit-ship-address">Edit</a>
                </div>`;

            // Append address HTML to the container
            addressContainer.append(addressHtml);


    }

    // Call the function to get the data and populate addresses
    getAddressData();

    // Attach event listener for edit buttons
    $(document).on('click', '.ship-b__edit', function (e) {
        e.preventDefault();
        // get the address data the pass it to the modal
        const addressIndex = $(this).closest('.ship-b__box').index();
        const address = addresses[addressIndex];
        console.log('Edit address:', address);
        populateAddressModal(addresses);


        $('#edit-ship-address').modal('show');
    });

    $(document).on('submit', '.checkout-modal2__form', function (e) {
        e.preventDefault();

        console.log('Form submitted!');

        // Retrieve the selected address ID
        const selectedAddressId = $('input[name="selected-address"]:checked').val();

        // Check if a radio button is selected
        if (!selectedAddressId) {
            console.log('No address selected.');
            return;
        }

        // Find the corresponding address object
        const selectedAddress = addresses.find(address => address.id === parseInt(selectedAddressId));

        currentSelectedAddressId = selectedAddress.id;

        console.log('Selected address id :', currentSelectedAddressId); // Log the selected address object

        // Check if the address object is found
        if (!selectedAddress) {
            console.log('Selected address not found.');
            return;
        }

        // Update the displayed address in the shipping container
        updateDisplayedAddress(selectedAddress);

        // Close the modal
        $('#edit-ship-address').modal('hide');
    });




    function updateDisplayedAddress(address) {
        console.log('Address:', address); // Log the address object
        const addressContainer = $('.ship-b__addresses');
        const formattedAddress = `${address.street}, ${address.city} ${address.state}-${address.zipCode} ${address.country}`;

        // Update the HTML of the shipping address container
        addressContainer.html(`
            <div class="ship-b__box u-s-m-b-10">
                <p class="ship-b__p">${formattedAddress} (+0) ${userData.phone}</p>
                <a class="ship-b__edit btn--e-transparent-platinum-b-2" data-modal="modal" data-modal-id="#edit-ship-address">Edit</a>
            </div>
        `);
    }



    function populateAddressModal(addresses, selectedIndex) {
        const tbody = $('#shipping-addresses-body');

        // Clear existing content
        tbody.empty();

        // Iterate through addresses and create table rows
        addresses.forEach(function (address, index) {
            const checked = index === selectedIndex ? 'checked' : ''; // Check the radio button if it's the selected address
            const formattedAddress = `${address.street}, ${address.city} ${address.state}-${address.zipCode} ${address.country}`;
            const region = `${address.city} ${address.state} - ${address.country}`;

            const row = `
                <tr>
                    <td><input type="radio" name="selected-address" value="${address.id}" ${checked}></td>
                    <td>${userData.name}</td>
                    <td>${formattedAddress}</td>
                    <td>${region}</td>
                    <td>${userData.phone}</td>
                </tr>
            `;

            tbody.append(row);
        });
    }


    $('#checkout-form').submit(function (event) {
        event.preventDefault();
        console.log('Checkout form submitted');
        $.ajax({
            url: 'checkout',
            type: 'POST',
            data: {addressId : currentSelectedAddressId},
            success: function (response) {
                    alert('Checkout successful!');
                    sessionStorage.removeItem('shoppingData');
                    sessionStorage.removeItem('total');
                    window.location.href = 'home';

            },
            error: function (xhr, status, error) {
                alert(xhr.responseText);
                console.error(xhr.responseText);
            }
        });
    });

    let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));

    if (shoppingData && shoppingData.products) {
        let container = $('.o-summary__item-wrap');

        shoppingData.products.forEach(function (product) {
            let productCard = `
                <div class="o-card" data-product-id="${product.productId}">
                    <div class="o-card__flex">
                        <div class="o-card__img-wrap">
                            <img class="u-img-fluid" src="${product.images[0]}" alt="">
                        </div>
                        <div class="o-card__info-wrap">
                            <span class="o-card__name">
                                <a href="product-detail.html">${product.name}</a>
                            </span>
                            <span class="o-card__quantity">Quantity x ${shoppingData.productCounts[product.name]}</span>
                            <span class="o-card__price">$${product.price}</span>
                        </div>
                    </div>
                    <a class="o-card__del far fa-trash-alt"></a>
                </div>`;
            container.append(productCard);
        });
    }

    $(document).on('click', '.o-card__del', function () {
        let productCard = $(this).closest('.o-card');
        let productId = productCard.data('product-id');

        console.log(productId);

        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {
                action: 'removeProduct',
                productId: productId
            },
            success: function (response) {
                if (response === 'true') {
                    productCard.remove();
                    deleteProductFromSessionStorage(productId); // Remove the product from the session storage
                    alert('Product removed from the cart!');
                } else {
                    alert('Failed to remove product from the cart.');
                }
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });

    function deleteProductFromSessionStorage(productId) {
        let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
        if (shoppingData) {
            let productIndex = shoppingData.products.findIndex(product => product.productId === productId);
            if (productIndex !== -1) {
                shoppingData.products.splice(productIndex, 1);
                sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
            }
        }
    }
});
