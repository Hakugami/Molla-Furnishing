$(document).ready(function() {
    $('#signin-form').on('submit', function(event) {
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
            success: function(data) {
                // handle success
                console.log(data);
            },
            error: function(error) {
                // handle error
                console.log(error);
            }
        });
    });
});