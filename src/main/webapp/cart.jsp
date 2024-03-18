<%@page session="false" contentType="text/html" pageEncoding="utf-8"%>

<jsp:include page="/header.jsp" />


        <!--====== App Content ======-->
        <div class="app-content">

            <!--====== Section 1 ======-->
            <div class="u-s-p-y-60">

                <!--====== Section Content ======-->
                <div class="section__content">
                    <div class="container">
                        <div class="breadcrumb">
                            <div class="breadcrumb__wrap">
                                <ul class="breadcrumb__list">
                                    <li class="has-separator">

                                        <a href="index.html">Home</a></li>
                                    <li class="is-marked">

                                        <a href="cart.html">Cart</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Section 1 ======-->


            <!--====== Section 2 ======-->
            <div class="u-s-p-b-60">

                <!--====== Section Intro ======-->
                <div class="section__intro u-s-m-b-60">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="section__text-wrap">
                                    <h1 class="section__heading u-c-secondary">SHOPPING CART</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--====== End - Section Intro ======-->


                <!--====== Section Content ======-->
                <div class="section__content">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 u-s-m-b-30">
                                <div class="table-responsive">
                                    <table class="table-p">
                                        <tbody>

                                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

                                            <script type="text/javascript">
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

                                                $(document).on('click', '.input-counter__minus', function () {
                                                    let input = $(this).siblings('.input-counter__text');
                                                    let count = parseInt(input.val());
                                                    let min = parseInt(input.attr('data-min'));

                                                    if (count > min) {
                                                        count--;
                                                        input.val(count);
                                                        updateProductCount(input, count);
                                                        updateTotalSum();
                                                    }
                                                });

                                                $(document).on('click', '.input-counter__plus', function () {
                                                    let input = $(this).siblings('.input-counter__text');
                                                    let count = parseInt(input.val());
                                                    let max = parseInt(input.attr('data-max'));

                                                    count++;
                                                    input.val(count);
                                                    updateProductCount(input, count);
                                                    updateTotalSum();
                                                });
                                                
                                                $(document).on('click', '.table-p__delete-link', function() {
                                                    let productName = $(this).closest('tr').find('.table-p__name a').text();
                                                    
                                                    // Remove the product from the shopping cart
                                                    shopping_products = shopping_products.filter(product => product.name !== productName);
                                                    
                                                    // Update the shopping data in session storage
                                                    let shoppingData = {
                                                        products: shopping_products,
                                                        productCounts: productCounts
                                                    };
                                                    sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
                                                    
                                                    // Remove the table row from the HTML
                                                    $(this).closest('tr').remove();
                                                    
                                                    // Update the total sum
                                                    updateTotalSum();
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

                                            </script>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="route-box">
                                    <div class="route-box__g1">

                                        <a class="route-box__link" href="shop-side-version-2.html"><i class="fas fa-long-arrow-alt-left"></i>

                                            <span>CONTINUE SHOPPING</span></a></div>
                                    <div class="route-box__g2">

                                        <a class="route-box__link" href="cart.html"><i class="fas fa-trash"></i>

                                            <span>CLEAR CART</span></a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--====== End - Section Content ======-->
            </div>
            <!--====== End - Section 2 ======-->


            <!--====== Section 3 ======-->
            <div class="u-s-p-b-60">

                <!--====== Section Content ======-->
                <div class="section__content">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 u-s-m-b-30">
                                <form class="f-cart">
                                    <div class="row">
                                        <div class="col-lg-4 col-md-6 u-s-m-b-30">
                                            <div class="f-cart__pad-box">
                                                <div class="u-s-m-b-30">
                                                    <table class="f-cart__table">
                                                        <tbody>
                                                            <tr>
                                                                <td>TOTAL</td>
                                                                <td id="total">$379.00</td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div>

                                                    <button class="btn btn--e-brand-b-2" type="submit"> PROCEED TO CHECKOUT</button></div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--====== End - Section Content ======-->
            </div>
            <!--====== End - Section 3 ======-->
        </div>
        <!--====== End - App Content ======-->


        <!--====== Main Footer ======-->
    <jsp:include page="/footer.jsp" />
    </div>

    <!--====== End - Main App ======-->

    <script>
        function updateTotalSum() {
            let shoppingData = JSON.parse(sessionStorage.getItem('shoppingData'));
            let shoppingProducts = shoppingData.products;
            let productCounts = shoppingData.productCounts;
            let totalSum = 0;

            if (shoppingProducts && shoppingProducts.length > 0) {
                shoppingProducts.forEach(function(product) {
                    let count = productCounts[product.name] || 1; 
                    totalSum += product.price * count;
                });
            }
            let subtotal = totalSum;
            document.getElementById("total").innerText = "$" + subtotal.toFixed(2);
            sessionStorage.setItem('total', subtotal.toFixed(2));
        }

        updateTotalSum();

        function clearShoppingCart() {
            shopping_products = []; 
            productCounts = {}; 
            sessionStorage.removeItem('shoppingData');

            $('.table-p tbody').empty(); 
        }

        $(document).on('click', '.route-box__link', function (event) {
            event.preventDefault();
            if ($(this).text().trim() === 'CLEAR CART') {
                clearShoppingCart();
            }
        });
    </script>
    <!--====== End - Main App ======-->

    <script>
        window.ga = function() {
            ga.q.push(arguments)
        };
        ga.q = [];
        ga.l = +new Date;
        ga('create', 'UA-XXXXX-Y', 'auto');
        ga('send', 'pageview')
    </script>
    <script src="https://www.google-analytics.com/analytics.js" async defer></script>

    <!--====== Vendor Js ======-->
    <script src="js/vendor.js"></script>

    <!--====== jQuery Shopnav plugin ======-->
    <script src="js/jquery.shopnav.js"></script>

    <!--====== App ======-->
    <script src="js/app.js"></script>

    <!--====== Noscript ======-->
    <noscript>
        <div class="app-setting">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="app-setting__wrap">
                            <h1 class="app-setting__h1">JavaScript is disabled in your browser.</h1>

                            <span class="app-setting__text">Please enable JavaScript in your browser or upgrade to a JavaScript-capable browser.</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </noscript>
</body>
</html>