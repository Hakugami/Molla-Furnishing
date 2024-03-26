$(document).ready(function () {


    $('.mini-product-container').empty();
    // Call the function to load products to mini cart when the document is ready
    loadProductsToMiniCart();

    $(document).on('click', '.mini-product__delete-link', function() {
        let productId = $(this).data('product-id');
        console.log('Removing product with ID:', productId);
        removeFromCart(productId);
        loadProductsToMiniCart(); // Reload mini cart after removing the product
        updateTotalSumProMaxUltra(); // Update the total sum

        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {
                action: 'removeProduct',
                productId: productId  // Corrected parameter name to match backend
            },
            success: function (response) {
                if (response === 'true') {
                    alert('Product removed from the cart!');
                } else {
                    alert('Failed to remove product from the cart.');
                }
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });

    function removeFromCart(productId) {
        let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
        if (shoppingData) {
            let productIndex = shoppingData.products.findIndex(product => product.productId === productId);
            if (productIndex !== -1) {
                shoppingData.products.splice(productIndex, 1);
                sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
            }
        }
    }


    function updateTotalSumProMaxUltra() {
        let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
        if (shoppingData && shoppingData.products) {
            let shoppingProducts = shoppingData.products;
            let productCounts = shoppingData.productCounts;
            let totalSum = 0;

            if (shoppingProducts.length > 0) {
                shoppingProducts.forEach(function (product) {
                    let count = productCounts[product.productId] || 1;
                    totalSum += product.price * count;
                });
            }
            let subtotal = totalSum;
            console.log('Subtotal:', subtotal);
            $('.subtotal-value').text('$' + subtotal.toFixed(2)); // Update subtotal value in HTML
            sessionStorage.setItem('total', subtotal.toFixed(2));
        } else {
            console.log('No shopping data found.');
            $('.subtotal-value').text('$0.00'); // Set default value if subtotal is not available
            sessionStorage.setItem('total', "0.00");
        }
    }
});


function loadProductsToMiniCart() {
    let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
    if (shoppingData && shoppingData.products && shoppingData.products.length > 0) {
        let miniProductContainer = $('.mini-product-container');
        miniProductContainer.empty(); // Clear existing products

        let productCounts = shoppingData.productCounts;


        shoppingData.products.forEach(function (product) {
            let count = productCounts[product.productId] || 1;

            let miniProductHtml = `
                    <div class="card-mini-product">
                        <div class="mini-product">
                            <div class="mini-product__image-wrapper">
                                <a class="mini-product__link" href="ProductPage?name=${product.name}">
                                    <img class="u-img-fluid" src="${product.images[0]}" alt="">
                                </a>
                            </div>
                            <div class="mini-product__info-wrapper">
                                <span class="mini-product__category">
                                    <a href="product?category=${product.categoryName}">${product.categoryName}</a>
                                </span>
                                <span class="mini-product__name">
                                    <a href="ProductPage?id=${product.name}">${product.name}</a>
                                </span>
                                <span class="mini-product__quantity">${count} x</span>
                                <span class="mini-product__price">$${product.price}</span>
                            </div>
                        </div>
                    <!-- Adjusted HTML code for the delete button -->
                    <a class="mini-product__delete-link far fa-trash-alt" data-product-id="${product.productId}"></a>
                    </div>`;

            miniProductContainer.append(miniProductHtml);
        });

        // Show the total number of products in the cart
        $('.total-item-round').text(shoppingData.products.length);
    } else {
        // Hide the element if there are no products in the cart
        $('.total-item-round').hide();
    }

    // Get subtotal from local storage
    let subtotal = parseFloat(sessionStorage.getItem('total'));
    if (!isNaN(subtotal)) {
        $('.subtotal-value').text('$' + subtotal.toFixed(2));
    } else {
        $('.subtotal-value').text('$0.00'); // Set default value if subtotal is not available
    }
}