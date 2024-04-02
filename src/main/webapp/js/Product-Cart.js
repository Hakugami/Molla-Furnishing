$(document).ready(function () {
    $.ajax({
        url: 'cart',
        type: 'POST',
        data: { action: 'retrieveCart' },
        dataType: 'json',
        success: function (data) {
            console.log('Cart data:', data);

            // Check if data is an array with length greater than 0
            if (Array.isArray(data) && data.length > 0) {
                let shoppingData = {
                    products: data.map(item => item.product), // Extract the product from each item
                    productCounts: {}
                };

                data.forEach(function (item) {
                    shoppingData.productCounts[item.product.name] = item.quantity;
                });

                sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
                updateTotalSum();
            }
        },
        error: function (xhr, status, error) {
            toastr.error('Failed to retrieve shopping cart data.');
            console.error(xhr.responseText);
        }
    });



    function loadProductsFromSessionStorage() {
        let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
        if (shoppingData && shoppingData.products && shoppingData.products.length > 0) {
            shoppingData.products.forEach(function (product) {
                let count = shoppingData.productCounts[product.name] || 1;
                let productElement = '<tr data-product-id="' + product.productId + '">' +
                    '<td>' +
                    '<div class="table-p__box">' +
                    '<div class="table-p__img-wrap">' +
                    '<img class="u-img-fluid" src="' + product.images[0] + '" alt="">' +
                    '</div>' +
                    '<div class="table-p__info">' +
                    '<span class="table-p__name">' +
                    '<a href="ProductPage?name=' + product.name + '">' + product.name + '</a>' +
                    '</span>' +
                    '<span class="table-p__category">' +
                    '<a href="poduct?category=' + product.categoryName + '">' + product.categoryName + '</a>' +
                    '</span>' +
                    '<ul class="table-p__variant-list">' +
                    '<li>' +
                    '<span>Size: ' + product.description + '</span>' +
                    '</li>' +
                    '<li>' +
                    '<span>Color: ' + product.productDetails.color+ '</span>' +
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

    setTimeout(loadProductsFromSessionStorage, 500);
});

$(document).on('click', '.input-counter__minus', function () {
    let input = $(this).siblings('.input-counter__text');
    let count = parseInt(input.val());
    let min = parseInt(input.attr('data-min'));

    let productId = input.closest('tr').data('product-id');

    console.log(productId + " " + count + " " + min);


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
            success: function (response) {
                if (response === 'true') {
                    toastr.success('Product quantity decremented successfully!');
                } else {
                    toastr.error('Failed to decrement product quantity.');
                }
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    }
});


$(document).on('click', '.input-counter__plus', function () {
    let input = $(this).siblings('.input-counter__text');
    let count = parseInt(input.val());
    let max = parseInt(input.attr('data-max'));

    let productId = input.closest('tr').data('product-id');
    console.log(productId + " " + count + " " + max);

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
                productId: productId,
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
    } else {
        toastr.error('Maximum quantity reached.');
    }
});


$(document).on('click', '.table-p__delete-link', function () {
    let productRow = $(this).closest('tr');
    let productId = productRow.data('product-id');

    productRow.remove(); // Remove the product row from the table
    deleteProductFromSessionStorage(productId); // Remove the product from the session storage
    updateTotalSum();    // Update the total sum

    console.log(productId);

    $.ajax({
        url: 'cart',
        type: 'POST',
        data: {
            action: 'removeProduct',
            productId: productId  // Corrected parameter name to match backend
        },
        success: function (response) {
            if (response === 'true') {
                toastr.success('Product removed from the cart!');
            } else {
                toastr.error('Failed to remove product from the cart.');
            }
        },
        error: function (xhr, status, error) {
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

function deleteProductFromSessionStorage(productId) {
    let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
    if (shoppingData) {
        let productIndex = shoppingData.products.findIndex(product => product.productId === productId);
        if (productIndex !== -1) {
            shoppingData.products.splice(productIndex, 1);
            sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
        }
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
        // Handle the case when there are no products in the shopping cart
        document.getElementById("total").innerText = "$0.00";
        sessionStorage.setItem('total', "0.00");
    }
}


updateTotalSum();

$(document).on('click', '.route-box__link', function (event) {
    event.preventDefault();
    if ($(this).text().trim() === 'CLEAR CART') {
        $('.table-p tbody').empty();
        clearSessionStorage();
        updateTotalSum();
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
        success: function (response) {
            if (response === 'true') {
                toastr.success('Shopping cart cleared successfully!');
            } else {
                toastr.error('Failed to clear shopping cart.');
            }
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}

function clearSessionStorage() {
    sessionStorage.removeItem('shoppingData');
    sessionStorage.removeItem('total');
}
