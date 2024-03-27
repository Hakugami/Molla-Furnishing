$(document).ready(function () {
    let params = new URLSearchParams(window.location.search);
    let name = params.get('name'); // replace 'name' with your actual parameter name

    $.ajax({
        url: 'RetrieveProducts/',
        type: 'GET',
        dataType: 'json',
        data: { name: name },
        success: function (data) {
            let product = data[0];
            console.log(product);
            // Iterate over product images
            for (let i = 0; i < Math.min(product.images.length, 5); i++) {
                // Update main image
                $(`#img-${i + 1}-wrap`).attr("data-src", product.images[i]);
                $(`#img-${i + 1}`).attr("src", product.images[i]).attr("data-zoom-image", product.images[i]);

                // Update thumbnail image
                $(`#thumb-${i + 1}`).attr("src", product.images[i]);
            }

            // Update category anchor tag
            $('#productCategory').attr('href', 'product?category=' + product.categoryName).text(product.categoryName);

            // Update subcategory anchor tag
            $('#productSubcategory').attr('href', 'product?subcategory=' + product.subCategoryName).text(product.subCategoryName);

            // Update product name anchor tag
            $('#productName').text(product.name);
            $('.pd-detail__name').text(product.name);
            $('.pd-detail__price').text('$' + product.price);
            $('.product-m__category a').text(product.categoryName);
            $('.pd-tab__desc p').text(product.description);
            $('.pd-detail__preview-desc').text(product.description);
            $('.pd-detail__stock').text(product.quantity === 0 ? 'Out of stock' : product.quantity + ' in stock');
            $('.pd-detail__discount').text('(69% OFF)');
            $('.pd-detail__del').text('$' + (product.price * 2));
            $('.pd-detail__rating').html(generateRatingStars(product.rating));
            $('.pd-detail__review').text('(' + product.ratings.length + ')');
            $('#product-material').text(product.productDetails.material);
            $('#product-color').text(product.productDetails.color);
            $('#product-dimensions').text(product.productDetails.dimensions);
            $('#product-weight').text(product.productDetails.weight);
        },
        error: function () {
            console.log('Error retrieving product.');
        }
    });

    $(document).on('submit', '.pd-detail__form', function (e) {
        e.preventDefault();
        let quantity = parseInt($('#product-quantity').val());
        if (quantity < 1) {
            alert('Please enter a valid quantity.');
            return;
        }

        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {
                action: 'addToCart',
                productId: name,
                quantity: quantity
            },
            success: function (data) {
                console.log('Product added to cart:', data);
                alert('Product added to cart!');
            },
            error: function (xhr, status, error) {
                console.error('Error adding product to cart:', error);
                console.error(xhr.responseText);
            }
        });
    });

});

function generateRatingStars(rating) {
    let stars = '';
    for (let i = 0; i < 5; i++) {
        if (i < rating) {
            stars += '<i class="fas fa-star"></i>';
        } else {
            stars += '<i class="far fa-star"></i>';
        }
    }
    return stars;
}