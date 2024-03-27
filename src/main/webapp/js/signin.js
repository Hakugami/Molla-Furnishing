$(document).ready(function () {
    // Function to send session storage data to the server
    function sendSessionDataToServer() {
        console.log('Sending session data to server...');
        // Retrieve data from session storage
        let shoppingData = sessionStorage.getItem('shoppingData');

        // Make sure shoppingData exists and is not empty
        if (shoppingData) {
            console.log('Session data:', shoppingData);

            $.ajax({
                url: 'cart', // Replace with your server endpoint
                type: 'POST',
                data: {
                    action: 'addProductsToCart', // Added action parameter to distinguish from 'addProduct' and 'removeProduct
                    shoppingData: shoppingData
                },
                success: function (response) {
                    console.log('Session data sent to server successfully.');
                },
                error: function (xhr, status, error) {
                    console.error('Failed to send session data to server:', error);
                }
            });
        }
    }

    // Google sign-in button click event
    $('#signin-google').on('click', function () {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signIn().then(function (googleUser) {
            var profile = googleUser.getBasicProfile();
            var email = profile.getEmail();
            var idToken = googleUser.getAuthResponse().id_token;
            $.ajax({
                url: 'loginFirebase', // replace with your login endpoint
                type: 'POST',
                data: {
                    email: email,
                    idToken: idToken
                },
                success: function (data) {
                    // Handle success
                    console.log(data);
                    window.location.href = 'home';
                    sendSessionDataToServer(); // Send session data to server
                    loadDataFromServer(); // Load data from server
                },
                error: function (error) {
                    // Handle error
                    console.log(error);
                    $('#login-error').text('Failed to login. Please check your email and password.');
                }
            });
        });
    });

    // Regular sign-in form submission event
    $('#signin-form').on('submit', function (event) {
        event.preventDefault();

        var email = $('#login-email').val();
        var password = $('#login-password').val();
        var rememberMe = $('#remember-me').is(':checked');

        $.ajax({
            url: 'login', // replace with your login endpoint
            type: 'POST',
            data: {
                email: email,
                password: password,
                rememberMe: rememberMe
            },
            success: function (data) {
                // Handle success
                console.log(data);

                if (data.message === 'admin') {
                    window.location.href = '/molla/view/admin/home';
                } else {
                    sendSessionDataToServer(); // Send session data to server
                    loadDataFromServer(); // Load data from server
                    window.location.href = 'home';
                }
            },
            error: function (error) {
                // Handle error
                console.log(error);
                $('#login-error').text('Failed to login. Please check your email and password.');
            }
        });
    });

    function loadDataFromServer() {
        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {action: 'retrieveCart'},
            dataType: 'json',
            success: function (data) {
                console.log('Cart data:', data);

                // Check if data is an array with length greater than 0
                if (Array.isArray(data) && data.length > 0) {
                    let shoppingData = {
                        products: data.map(item => item.product), // Extract the product from each item
                        productCounts: {}
                    };

                    data.forEach(function (item) {
                        shoppingData.productCounts[item.product.name] = item.quantity;
                    });

                    sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
                    console.log("test: " + "------------------------");
                    updateTotalSum();
                } else {
                    console.log('No data found in cart.');
                }
            },
            error: function (xhr, status, error) {
                console.error('Error retrieving cart:', error);
                console.error(xhr.responseText);
            }
        });
    }


    function updateTotalSum() {
        let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
        if (shoppingData && shoppingData.products) {
            let shoppingProducts = shoppingData.products;
            let productCounts = shoppingData.productCounts;
            let totalSum = 0;

            if (shoppingProducts.length > 0) {
                shoppingProducts.forEach(function (product) {
                    let count = productCounts[product.name] || 1;
                    totalSum += product.price * count;
                });
            }
            let subtotal = totalSum;
            console.log('Subtotal:', subtotal);
            sessionStorage.setItem('total', subtotal.toFixed(2));
        } else {
            console.log('No shopping data found.');
            sessionStorage.setItem('total', "0.00");
        }
    }

    updateTotalSum();
});
