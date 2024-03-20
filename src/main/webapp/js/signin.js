$(document).ready(function () {
        gapi.load('auth2', function () {
            gapi.auth2.init({
                client_id: '173881134231-cvsk83c83ih0aamnhrh9419qhlh5ounv.apps.googleusercontent.com'
            });
        });

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
                            // handle success
                            console.log(data);
                            window.location.href = 'home';
                        },
                        error: function (error) {
                            // handle error
                            console.log(error);
                            $('#login-error').text('Failed to login. Please check your email and password.');
                        }
                    });
                });
            }
        );
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
                // handle success
                console.log(data);
                window.location.href = 'home';

            },
            error: function (error) {
                // handle error
                console.log(error);
                $('#login-error').text('Failed to login. Please check your email and password.');

            }
        });
    });
});
