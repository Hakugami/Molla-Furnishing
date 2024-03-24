$(document).ready(function () {
    function loadProductsFromSessionStorage() {
        let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
        if (shoppingData && shoppingData.products && shoppingData.products.length > 0) {
            shoppingData.products.forEach(function (product) {
                let count = shoppingData.productCounts[product.name] || 1;
                let productElement = '<tr>' +
                    '<td>' +
                    '<div class="table-p__box">' +
                    '<div class="table-p__img-wrap">' +
                    '<img class="u-img-fluid" src="' + product.images[0] + '" alt="">' +
                    '</div>' +
                    '<div class="table-p__info">' +
                    '<span class="table-p__name">' +
                    '<a href="product-detail.html">' + product.name + '</a>' +
                    '</span>' +
                    '<span class="table-p__category">' +
                    '<a href="shop-side-version-2.html">' + product.category + '</a>' +
                    '</span>' +
                    '<ul class="table-p__variant-list">' +
                    '<li>' +
                    '<span>Size: ' + product.description + '</span>' +
                    '</li>' +
                    '<li>' +
                    '<span>Color: ' + product.rating + '</span>' +
                    '</li>' +
                    '</ul>' +
                    '</div>' +
                    '</div>' +
                    '</td>' +
                    '<td>' +
                    '<span class="table-p__price">$' + product.price + '</span>' +
                    '</td>' +
                    '<td>' +
                    '<div class="table-p__input-counter-wrap">' +
                    '<div class="input-counter">' +
                    '<span class="input-counter__minus fas fa-minus"></span>' +
                    '<input class="input-counter__text input-counter--text-primary-style" type="text" value="' + count + '" data-min="1" data-max="1000">' +
                    '<span class="input-counter__plus fas fa-plus"></span>' +
                    '</div>' +
                    '</div>' +
                    '</td>' +
                    '<td>' +
                    '<div class="table-p__del-wrap">' +
                    '<a class="far fa-trash-alt table-p__delete-link"></a>' +
                    '</div>' +
                    '</td>' +
                    '</tr>';

                $('.table-p tbody').append(productElement);
            });
        } else {
        }
    }

    loadProductsFromSessionStorage();
});

$(document).on('click', '.input-counter__minus', function (event) {
    event.preventDefault();
    let input = $(this).siblings('.input-counter__text');
    let count = parseInt(input.val());
    let min = parseInt(input.attr('data-min'));
    let productIndex = $(this).closest('.col-lg-3').index();
    let product = products[productIndex];
    let productId = product.productId;

    if (count > min) {
        count--;
        input.val(count);
        updateProductCount(input, count);
        updateTotalSum();

        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {
                action: 'decrementProductQuantity',
                productId: productId
            },
            success: function(response) {
                if (response >= 0) {
                    input.val(response);
                    alert('Product quantity decremented successfully!');
                } else {
                    alert('Failed to decrement product quantity.');
                }
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    }
});


$(document).on('click', '.input-counter__plus', function (event) {
    event.preventDefault();
    let productIndex = $(this).closest('.col-lg-3').index();
    let product = products[productIndex];
    let productId = product.productId;
    let input = $(this).siblings('.input-counter__text');
    let count = parseInt(input.val());
    let max = parseInt(input.attr('data-max'));


    if (count < max) {
        count++;
        input.val(count);
        updateProductCount(input, count);
        updateTotalSum();

        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {
                action: 'addProduct',
                productId: productId
            },
            success: function(response) {
                if (response === 'true') {
                    alert('Product quantity incremented successfully!');
                } else {
                    alert('Failed to increment product quantity.');
                }
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    } else {
        alert('Maximum quantity reached!');
    }
});



$(document).on('click', '.table-p__delete-link', function (event) {
    let productName = $(this).closest('tr').find('.table-p__name a').text();
                                                    
    shopping_products = shopping_products.filter(product => product.name !== productName);
    
    let shoppingData = {
        products: shopping_products,
        productCounts: productCounts
    };
    sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
    
    $(this).closest('tr').remove();
    
    updateTotalSum();
    event.preventDefault();
    let productIndex = $(this).closest('.col-lg-3').index();
    let product = products[productIndex];

    let productId = product.productId;
    $.ajax({
        url: 'cart',
        type: 'POST',
        data: {
            action: 'removeProduct',
            productId: productId,
        },
        success: function(response) {
            if (response === 'true') {
                alert('Product removed from the cart!');
                productRow.remove();
                updateTotalSum();
            } else {
                alert('Failed to remove product from the cart.');
            }
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
});



function updateProductCount(input, count) {
    let productName = input.closest('tr').find('.table-p__name a').text();
    let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
    if (shoppingData) {
        shoppingData.productCounts[productName] = count;
        sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
        console.log('Product Counts:', shoppingData.productCounts);
    }
}


function updateTotalSum() {
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
        document.getElementById("total").innerText = "$" + subtotal.toFixed(2);
        sessionStorage.setItem('total', subtotal.toFixed(2));
    } else {
        document.getElementById("total").innerText = "$0.00";
        sessionStorage.setItem('total', "0.00");
    }
}


updateTotalSum();

$(document).on('click', '.route-box__link', function (event) {
    event.preventDefault();
    if ($(this).text().trim() === 'CLEAR CART') {
        clearShoppingCart();
    }
});

function clearShoppingCart() {
    $.ajax({
        url: 'cart',
        type: 'POST',
        data: {
            action: 'clearCart'
        },
        success: function(response) {
            if (response === 'true') {
                alert('Shopping cart cleared successfully!');
                $('.table-p tbody').empty();
                updateTotalSum();
            } else {
                alert('Failed to clear shopping cart.');
            }
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}
