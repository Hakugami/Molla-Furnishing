$(document).ready(function () {
    // Function to send session storage data to the server
    function sendSessionDataToServer() {
        console.log('Sending session data to server...');
        // Retrieve data from session storage
        let shoppingData = sessionStorage.getItem('shoppingData');

        // Make sure shoppingData exists and is not empty
        if (shoppingData) {
            console.log('Session data:', shoppingData);
            // Make an HTTP request to send the data to the server
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
                sendSessionDataToServer(); // Send session data to server
                window.location.href = 'home';
            },
            error: function (error) {
                // Handle error
                console.log(error);
                $('#login-error').text('Failed to login. Please check your email and password.');
            }
        });
    });
});
