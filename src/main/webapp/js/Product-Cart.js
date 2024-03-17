$(document).ready(function () {
    function loadProductsIntoCart() {
        let shoppingProducts = JSON.parse(sessionStorage.getItem('shopping_products'));
        if (shoppingProducts && shoppingProducts.length > 0) {
            shoppingProducts.forEach(function (product) {
                let productElement = '<tr>' +
                                        '<td>' +
                                            '<div class="table-p__box">' +
                                                '<div class="table-p__img-wrap">' +
                                                    '<img class="u-img-fluid" src="' + product.imageSrc + '" alt="">' +
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
                                                            '<span>Size: ' + product.size + '</span>' +
                                                        '</li>' +
                                                        '<li>' +
                                                            '<span>Color: ' + product.color + '</span>' +
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
                                                    '<input class="input-counter__text input-counter--text-primary-style" type="text" value="' + product.quantity + '" data-min="1" data-max="1000">' +
                                                    '<span class="input-counter__plus fas fa-plus"></span>' +
                                                '</div>' +
                                            '</div>' +
                                        '</td>' +
                                        '<td>' +
                                            '<div class="table-p__del-wrap">' +
                                                '<a class="far fa-trash-alt table-p__delete-link" href="#"></a>' +
                                            '</div>' +
                                        '</td>' +
                                    '</tr>';
                $('#cart-products tbody').append(productElement);
            });
        } else {
            $('#cart-products tbody').append('<tr><td colspan="4">No products in the shopping cart</td></tr>');
        }
    }

    loadProductsIntoCart();
});
