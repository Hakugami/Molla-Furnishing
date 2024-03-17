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
                                                    // Function to load products from session storage
                                                    function loadProductsFromSessionStorage() {
                                                        // Retrieve shopping products from session storage
                                                        let shoppingProducts = JSON.parse(sessionStorage.getItem('shopping_products'));
                                        
                                                        // Check if there are products in the shopping cart
                                                        if (shoppingProducts && shoppingProducts.length > 0) {
                                                            // Loop through each product and create HTML elements to display them
                                                            shoppingProducts.forEach(function (product) {
                                                                // Create HTML elements for each product
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
                                        
                                                                // Append the product element to the table
                                                                $('.table-p tbody').append(productElement);
                                                            });
                                                        } else {
                                                            // If there are no products in the shopping cart, display a message or hide the table
                                                            // For example:
                                                            // $('.table-p').hide(); // Hide the table
                                                            // $('.table-p tbody').append('<tr><td colspan="4">No products in the shopping cart</td></tr>'); // Display a message
                                                        }
                                                    }
                                        
                                                    // Call the function to load products from session storage
                                                    loadProductsFromSessionStorage();
                                                });
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

                                        <a class="route-box__link" href="cart.html"><i class="fas fa-sync"></i>

                                            <span>UPDATE CART</span></a></div>
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
                                                <h1 class="gl-h1">ESTIMATE SHIPPING AND TAXES</h1>

                                                <span class="gl-text u-s-m-b-30">Enter your destination to get a shipping estimate.</span>
                                                <div class="u-s-m-b-30">

                                                    <!--====== Select Box ======-->

                                                    <label class="gl-label" for="shipping-country">COUNTRY *</label><select class="select-box select-box--primary-style" id="shipping-country">
                                                        <option selected value="">Choose Country</option>
                                                        <option value="uae">United Arab Emirate (UAE)</option>
                                                        <option value="uk">United Kingdom (UK)</option>
                                                        <option value="us">United States (US)</option>
                                                    </select>
                                                    <!--====== End - Select Box ======-->
                                                </div>
                                                <div class="u-s-m-b-30">

                                                    <!--====== Select Box ======-->

                                                    <label class="gl-label" for="shipping-state">STATE/PROVINCE *</label><select class="select-box select-box--primary-style" id="shipping-state">
                                                        <option selected value="">Choose State/Province</option>
                                                        <option value="al">Alabama</option>
                                                        <option value="al">Alaska</option>
                                                        <option value="ny">New York</option>
                                                    </select>
                                                    <!--====== End - Select Box ======-->
                                                </div>
                                                <div class="u-s-m-b-30">

                                                    <label class="gl-label" for="shipping-zip">ZIP/POSTAL CODE *</label>

                                                    <input class="input-text input-text--primary-style" type="text" id="shipping-zip" placeholder="Zip/Postal Code"></div>
                                                <div class="u-s-m-b-30">

                                                    <a class="f-cart__ship-link btn--e-transparent-brand-b-2" href="cart.html">CALCULATE SHIPPING</a></div>

                                                <span class="gl-text">Note: There are some countries where free shipping is available otherwise our flat rate charges or country delivery charges will be apply.</span>
                                            </div>
                                        </div>
                                        <div class="col-lg-4 col-md-6 u-s-m-b-30">
                                            <div class="f-cart__pad-box">
                                                <h1 class="gl-h1">NOTE</h1>

                                                <span class="gl-text u-s-m-b-30">Add Special Note About Your Product</span>
                                                <div>

                                                    <label for="f-cart-note"></label><textarea class="text-area text-area--primary-style" id="f-cart-note"></textarea></div>
                                            </div>
                                        </div>
                                        <div class="col-lg-4 col-md-6 u-s-m-b-30">
                                            <div class="f-cart__pad-box">
                                                <div class="u-s-m-b-30">
                                                    <table class="f-cart__table">
                                                        <tbody>
                                                            <tr>
                                                                <td>SHIPPING</td>
                                                                <td>$4.00</td>
                                                            </tr>
                                                            <tr>
                                                                <td>TAX</td>
                                                                <td>$0.00</td>
                                                            </tr>
                                                            <tr>
                                                                <td>SUBTOTAL</td>
                                                                <td>$379.00</td>
                                                            </tr>
                                                            <tr>
                                                                <td>GRAND TOTAL</td>
                                                                <td>$379.00</td>
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


    <!--====== Google Analytics: change UA-XXXXX-Y to be your site's ID ======-->
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

    <!--====== Custom js ======-->
    <script src="js/Product-Cart.js"></script>

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