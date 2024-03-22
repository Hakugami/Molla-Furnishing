$(document).ready(function() {

    $('#reset-password-form').on('submit', function(e) {
        e.preventDefault();

        const email = $('#reset-email').val();

        $.ajax({
            type: 'POST',
            url: 'sendResetPassword',
            data: {email: email},
            success: function(response) {
               window.location.href = 'resetPasswordChange?email=' + email;
            },
            error: function(response) {
                console.log(response);

            }
        })

    });

});