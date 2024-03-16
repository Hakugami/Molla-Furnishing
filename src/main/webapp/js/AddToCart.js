function addToCart(productId) {
    var form = document.createElement('form');
    form.method = 'post';
    form.action = 'AddToCartServlet';

    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'productId';
    input.value = productId;

    form.appendChild(input);

    document.body.appendChild(form);

    form.submit();
}
