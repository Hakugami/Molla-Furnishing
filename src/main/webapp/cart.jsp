<%@page session="false" contentType="text/html" pageEncoding="utf-8"%>

<jsp:include page="/header.jsp" />
<div class="app-content">
    <div class="u-s-p-y-60">
        <div class="section__content">
            <div class="container">
                <div class="breadcrumb">
                    <div class="breadcrumb__wrap">
                        <ul class="breadcrumb__list">
                            <li class="has-separator">
                                <a href="home">Home</a></li>
                            <li class="is-marked">
                                <a href="cart">Cart</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="u-s-p-b-60">
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
        <div class="section__content">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 u-s-m-b-30">
                        <div class="table-responsive">
                            <table class="table-p">
                                <tbody>
                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
                                <a class="route-box__link" href="cart"><i class="fas fa-trash"></i>
                                    <span>CLEAR CART</span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="u-s-p-b-60">
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
                                                        <td id="total">$0.00</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div>
                                                <a class="btn btn--e-brand-b-2" href="checkout"> PROCEED TO CHECKOUT</a></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/footer.jsp" />
    </div>

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

    <!--====== Product-Cart ======-->
    <script src="js/sessionStorageUtils.js"></script>
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