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

                                    <a href="home">Home</a></li>
                                <li class="is-marked">

                                    <a href="dash-address-add.html">My Account</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--====== End - Section 1 ======-->


        <!--====== Section 2 ======-->
        <div class="u-s-p-b-60">

            <!--====== Section Content ======-->
            <div class="section__content">
                <div class="dash">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-3 col-md-12">

                                <!--====== Dashboard Features ======-->
                                <div class="dash__box dash__box--bg-white dash__box--shadow u-s-m-b-30">
                                    <div class="dash__pad-1">

                                        <span class="dash__text u-s-m-b-16"></span>
                                        <ul class="dash__f-list">
                                            <li>

                                                <a href="profile">Manage My Account</a></li>
                                            <li>

                                                <a href="myProfile">My Profile</a></li>
                                            <li>

                                                <a class="dash-active" href="address">Address Book</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <!--====== End - Dashboard Features ======-->
                            </div>
                            <div class="col-lg-9 col-md-12">
                                <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white">
                                    <div class="dash__pad-2">
                                        <h1 class="dash__h1 u-s-m-b-14">Add new Address</h1>

                                        <span class="dash__text u-s-m-b-30">We need an address where we could deliver products.</span>
                                        <form class="dash-address-manipulation">
                                            <div class="gl-inline">
                                                <div class="u-s-m-b-30">
                                                    <label class="gl-label" for="address-street">STREET ADDRESS
                                                        *</label>
                                                    <input class="input-text input-text--primary-style" type="text"
                                                           id="address-street" name="address-street"
                                                           placeholder="House Name and Street">
                                                </div>
                                            </div>
                                            <div class="gl-inline">
                                                <div class="u-s-m-b-30">
                                                    <label class="gl-label" for="country">COUNTRY *</label>
                                                    <select class="select-box select-box--primary-style"
                                                            onchange="print_state('state',this.selectedIndex);"
                                                            id="country" name="country">
                                                        <option selected value="">Choose Country</option>
                                                    </select>
                                                </div>
                                                <div class="u-s-m-b-30">
                                                    <label class="gl-label" for="state">STATE/PROVINCE *</label>
                                                    <select class="select-box select-box--primary-style" name="state"
                                                            id="state">
                                                        <option selected value="">Choose State/Province</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="gl-inline">
                                                <div class="u-s-m-b-30">
                                                    <label class="gl-label" for="address-city">TOWN/CITY *</label>
                                                    <input class="input-text input-text--primary-style" type="text"
                                                           id="address-city" name="address-city">
                                                </div>
                                                <div class="u-s-m-b-30">
                                                    <label class="gl-label" for="address-postal">ZIP/POSTAL CODE
                                                        *</label>
                                                    <input class="input-text input-text--primary-style" type="text"
                                                           id="address-postal" name="address-postal"
                                                           placeholder="Zip/Postal Code">
                                                </div>
                                            </div>
                                            <button class="btn btn--e-brand-b-2" type="submit">SAVE</button>
                                        </form>
                                    </div>
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


    <!--====== Main Footer ======-->
<jsp:include page="/footer.jsp" />

</div>
<!--====== End - Main App ======-->


<!--====== Google Analytics: change UA-XXXXX-Y to be your site's ID ======-->
<script>
    window.ga = function () {
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
<script src="js/variousCountryListFormats.js"></script>
<script type="text/javascript" src="js/countries.js"></script>
<script src="js/add-address.js"></script>

<script language="javascript">print_country("country");</script>


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