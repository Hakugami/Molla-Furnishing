$(document).ready(function () {

    // Add click event listener to all elements with class 'product-r__action-wrap'
    $('.product-r__action-wrap a[data-modal-id="#add-to-cart"]').click(function(event) {
        event.preventDefault(); // Prevent default link behavior
        var product = {
            name: $(this).data('product-name'),
            description: $(this).data('product-description'),
            price: $(this).data('product-price'),
            quantity: $(this).data('product-quantity'),
            images: [
                $(this).data('product-image')
            ],
            ratings: [],
            categoryName: $(this).data('product-category-name'),
            rating: 0,
            productId: $(this).data('product-id'),
            dateAdded: null,
            productDetails: {
                material: null,
                dimensions: null,
                color: $(this).data('product-color'),
                weight: null
            },         
        };
        addToCart(product); // Call function to add product to cart
    });

    let shoppingData = getShoppingData();
    let shopping_products = shoppingData.products;
    let productCounts = shoppingData.productCounts;

    function addToCart(product) {

        let productId = product.productId;

        let productAlreadyInCart = shopping_products.some(item => item.name === product.name);

        if (product.quantity === 0) {
            alert("Product is out of stock");
            return;
        }

        if (!productAlreadyInCart) {
            shopping_products.push(product);
        }

        productCounts[product.name] = (productCounts[product.name] || 0) + 1;

        // Store updated product counts and shopping products in sessionStorage
        updateShoppingData({ products: shopping_products, productCounts: productCounts });


        sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
        console.log('Shopping Products:', shopping_products);
        console.log('Product added to cart:', product);
        console.log('Product Counts:', productCounts);
        updateTotalSumProMax();
        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {
                action: 'addProduct',
                productId: productId,
                quantity: 1
            },
            success: function (response) {
                if (response === 'true') {
                    alert('Product quantity incremented successfully!');
                } else {
                    alert('Failed to increment product quantity.');
                }
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    }



    function updateTotalSumProMax() {
        let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
        if (shoppingData && shoppingData.products) {
            let shoppingProducts = shoppingData.products;
            let productCounts = shoppingData.productCounts;
            let totalSum = 0;

            if (shoppingProducts.length > 0) {
                shoppingProducts.forEach(function (product) {
                    let count = productCounts[product.name] || 1;
                    totalSum += product.price * count;
                });
            }
            let subtotal = totalSum;
            console.log('Subtotal:', subtotal);
            sessionStorage.setItem('total', subtotal.toFixed(2));
        } else {
            console.log('No shopping data found.');
            sessionStorage.setItem('total', "0.00");
        }
    }
});
