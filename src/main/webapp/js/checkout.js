$(document).ready(function(){
    $('#checkout-form').submit(function(event){
        event.preventDefault();
        console.log('Checkout form submitted');
        $.ajax({
            url: 'checkout',
            type: 'POST',
            success: function(response){
                console.log(response);
            },
            error: function(xhr, status, error){
                console.error(xhr.responseText);
            }
        });
    });
});