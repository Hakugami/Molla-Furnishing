$(document).ready(function(){
    $(".checkout-f__payment").on("submit", function(e){
        e.preventDefault();

        $.ajax({
            type: "POST",
            url: 'checkout',
            success: function(response){
                if(response.ok){
                    alert("Payment successful");
                    window.location.href = "home";
                }else{
                    alert("Payment failed");
                }
            }
        });
    });
});