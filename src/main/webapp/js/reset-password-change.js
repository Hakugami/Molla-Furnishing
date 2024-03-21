$(document).ready(function() {

        const params = new URLSearchParams(window.location.search);
        const email = params.get('email');
        console.log(email);
$('#reset-password-change-form').on('submit', function(e) {
        e.preventDefault();

        const newPassword = $('#reset-password').val();
        const confirmNewPassword = $('#reset-password-confirm').val();
        const confirmationToken = $('#reset-password-token').val();

        if (newPassword !== confirmNewPassword) {
            alert("Passwords do not match");
            return;
        }

        $.ajax({
            type: 'POST',
            url: 'resetPasswordChange',
            data: {newPassword: newPassword, confirmationToken: confirmationToken , email: email},
            success: function(response) {
                window.location.href = 'login';
            },
            error: function(response) {
                console.log(response);

            }
        })

    });


});