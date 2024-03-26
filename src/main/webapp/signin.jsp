<%@page session="false" contentType="text/html" pageEncoding="utf-8" %>

<jsp:include page="/header.jsp"/>

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

                                <a href="home">Home</a></li>
                            <li class="is-marked">

                                <a href="login">Signin</a></li>
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
                            <h1 class="section__heading u-c-secondary">ALREADY REGISTERED?</h1>
                            <span class="section__span u-c-brand" id="login-error"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--====== End - Section Intro ======-->


        <!--====== Section Content ======-->
        <div class="section__content">
            <div class="container">
                <div class="row row--center">
                    <div class="col-lg-6 col-md-8 u-s-m-b-30">
                        <div class="l-f-o">
                            <div class="l-f-o__pad-box">
                                <h1 class="gl-h1">I'M NEW CUSTOMER</h1>

                                <span class="gl-text u-s-m-b-30">By creating an account with our store, you will be able to move through the checkout process faster, store shipping addresses, view and track your orders in your account and more.</span>
                                <div class="u-s-m-b-15">

                                    <a class="l-f-o__create-link btn--e-transparent-brand-b-2" href="register">CREATE AN
                                        ACCOUNT</a></div>
                                <h1 class="gl-h1">SIGNIN</h1>

                                <span class="gl-text u-s-m-b-30">If you have an account with us, please log in.</span>
                                <form class="l-f-o__form" id="signin-form">
                                    <div class="gl-s-api">
                                        <div class="u-s-m-b-15">

                                            <button id="signin-google" class="gl-s-api__btn gl-s-api__btn--gplus"
                                                    type="button"><i class="fab fa-google"></i>

                                                <span>Signin with Google</span></button>
                                        </div>
                                    </div>
                                    <div class="u-s-m-b-30">

                                        <label class="gl-label" for="login-email">E-MAIL *</label>

                                        <input class="input-text input-text--primary-style" type="email"
                                               id="login-email" placeholder="Enter E-mail"></div>
                                    <div class="u-s-m-b-30">

                                        <label class="gl-label" for="login-password">PASSWORD *</label>

                                        <input class="input-text input-text--primary-style" type="password"
                                               id="login-password" placeholder="Enter Password"></div>
                                    <div class="gl-inline">
                                        <div class="u-s-m-b-30">

                                            <button class="btn btn--e-transparent-brand-b-2" type="submit">LOGIN
                                            </button>
                                        </div>
                                        <div class="u-s-m-b-30">

                                            <a class="gl-link" href="sendResetPassword">Lost Your Password?</a></div>
                                    </div>
                                    <div class="u-s-m-b-30">

                                        <!--====== Check Box ======-->
                                        <div class="check-box">

                                            <input type="checkbox" id="remember-me">
                                            <div class="check-box__state check-box__state--primary">

                                                <label class="check-box__label" for="remember-me">Remember Me</label>
                                            </div>
                                        </div>
                                        <!--====== End - Check Box ======-->
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--====== End - Section Content ======-->
    </div>
    <!--====== End - Section 2 ======-->
</div>
<!--====== End - App Content ======-->


<jsp:include page="/footer.jsp"/>

</div>
<!--====== End - Main App ======-->



<!--====== Vendor Js ======-->
<script src="js/vendor.js"></script>

<!--====== jQuery Shopnav plugin ======-->
<script src="js/jquery.shopnav.js"></script>

<!--====== App ======-->
<script src="js/app.js"></script>

<!--====== Custom js ======-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://apis.google.com/js/platform.js" async defer></script>

<script src="https://accounts.google.com/gsi/client" async defer></script>

<script src="js/signin.js"></script>

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