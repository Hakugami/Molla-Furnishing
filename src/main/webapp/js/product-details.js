$(document).ready(function () {

    let shoppingData = getShoppingData();
    let shopping_products = shoppingData.products;
    let productCounts = shoppingData.productCounts;
    let currentProduct = null;
    let params = new URLSearchParams(window.location.search);
    let name = params.get('name'); // replace 'name' with your actual parameter name

    $.ajax({
        url: 'RetrieveProducts/',
        type: 'GET',
        dataType: 'json',
        data: { name: name },
        success: function (data) {
            let product = data[0];
            currentProduct = product;
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

    // get the product id from session storage
    $(document).on('submit', '.pd-detail__form', function (e) {
        e.preventDefault();
        event.preventDefault();
        let productId = currentProduct.productId;
        let quantity =$('.input-counter__text').val();

        if (quantity === '') {
            toastr.error('Please enter a valid quantity.');
            return; // exit the function and do nothing else
        }

        productCounts[currentProduct.name] = (productCounts[currentProduct.name] || 0) + parseInt(quantity);

        updateShoppingData({ products: currentProduct, productCounts: productCounts });



        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {
                action: 'addProduct',
                productId: productId,
                quantity: quantity
            },
            success: function (response) {
                if (response === 'true') {
                   toastr.success('Product added to the cart!');
                } else {
                    toastr.error('Failed to add product to the cart.');
                }
            },
            error: function (xhr, status, error) {
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