$(document).ready(function() {
    $('.dash-address-manipulation').on('submit', function(event) {
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
                // Redirect to address page
                window.location.href = 'profile';
            },
            error: function(error) {
                // handle error
                console.error(error);
            }
        });
    });
});