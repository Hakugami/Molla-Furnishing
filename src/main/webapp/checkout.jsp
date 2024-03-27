<%@page session="false" contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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

                                    <a href="checkout">Checkout</a></li>
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
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div id="checkout-msg-group">

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
                    <div class="checkout-f">
                        <div class="row">
                            <div class="col-lg-6">
                                <h1 class="checkout-f__h1">DELIVERY INFORMATION</h1>
                                <form class="checkout-f__delivery">
                                    <div class="u-s-m-b-30">
                                        <div class="u-s-m-b-15">


                                        </div>


                                        <!--====== Street Address ======-->
                                        <div class="u-s-m-b-15">

                                            <label class="gl-label" for="address-street">STREET ADDRESS *</label>

                                            <input class="input-text input-text--primary-style" type="text"
                                                   name="address-street" id="address-street"
                                                   placeholder="House name and street name" data-bill=""></div>

                                        <!--====== End - Street Address ======-->


                                        <!--====== Country ======-->
                                        <div class="u-s-m-b-15">

                                            <!--====== Select Box ======-->

                                            <label class="gl-label" for="country">COUNTRY *</label>
                                            <select class="select-box select-box--primary-style"
                                                    onchange="print_state('state',this.selectedIndex);"
                                                    id="country" name="country">
                                                <option selected value="">Choose Country</option>
                                            </select>
                                            <!--====== End - Select Box ======-->
                                        </div>
                                        <!--====== End - Country ======-->


                                        <!--====== Town / City ======-->
                                        <div class="u-s-m-b-15">

                                            <label class="gl-label" for="address-city">TOWN/CITY *</label>

                                            <input class="input-text input-text--primary-style" type="text"
                                                   name="address-city" id="address-city" data-bill=""></div>
                                        <!--====== End - Town / City ======-->


                                        <!--====== STATE/PROVINCE ======-->
                                        <div class="u-s-m-b-15">

                                            <!--====== Select Box ======-->

                                            <label class="gl-label" for="state">STATE/PROVINCE *</label>
                                            <select class="select-box select-box--primary-style" name="state"
                                                    id="state">
                                                <option selected value="">Choose State/Province</option>
                                            </select>
                                            <!--====== End - Select Box ======-->
                                        </div>
                                        <!--====== End - STATE/PROVINCE ======-->


                                        <!--====== ZIP/POSTAL ======-->
                                        <div class="u-s-m-b-15">

                                            <label class="gl-label" for="address-postal">ZIP/POSTAL CODE *</label>

                                            <input class="input-text input-text--primary-style" type="text"
                                                   name="address-postal" id="address-postal"
                                                   placeholder="Zip/Postal Code" data-bill=""></div>
                                        <!--====== End - ZIP/POSTAL ======-->
                                        <div class="u-s-m-b-10">


                                        </div>


                                        <div>

                                            <button class="btn btn--e-transparent-brand-b-2" type="submit">SAVE</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-lg-6">
                                <h1 class="checkout-f__h1">ORDER SUMMARY</h1>

                                <!--====== Order Summary ======-->
                                <div class="o-summary">
                                    <div class="o-summary__section u-s-m-b-30">
                                        <div class="o-summary__item-wrap gl-scroll">

                                        </div>
                                    </div>
                                    <div class="o-summary__section u-s-m-b-30">
                                        <div class="o-summary__box">
                                            <h1 class="checkout-f__h1">SHIPPING & BILLING</h1>
                                            <div class="ship-b">
                                                <span class="ship-b__text">Ship to:</span>
                                                <!-- Container for shipping addresses -->
                                                <div class="ship-b__addresses">
                                                    <!-- Addresses will be dynamically inserted here -->
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="o-summary__section u-s-m-b-30">
                                        <div class="o-summary__box">
                                            <table class="o-summary__table">
                                                <tbody>
                                                <tr>
                                                    <td>YOUR CREDIT</td>
                                                    <td id="credit-before">$0.00</td>
                                                </tr>
                                                <tr>
                                                    <td>SUBTOTAL</td>
                                                    <td id="subtotal">$379.00</td>
                                                </tr>
                                                <tr>
                                                    <td>YOUR CREDIT AFTER</td>
                                                    <td id="credit-after">$0.00</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="o-summary__section u-s-m-b-30">
                                        <div class="o-summary__box">
                                            <h1 class="checkout-f__h1">PAYMENT INFORMATION</h1>
                                            <form id="checkout-form" class="checkout-f__payment">
                                                <div class="u-s-m-b-10">

                                                    <!--====== Radio Box ======-->
                                                    <div class="radio-box">

                                                        <input type="radio" id="cash-on-delivery" name="payment">
                                                        <div class="radio-box__state radio-box__state--primary">

                                                            <label class="radio-box__label" for="cash-on-delivery">Cash
                                                                on Delivery</label></div>
                                                    </div>
                                                    <!--====== End - Radio Box ======-->

                                                    <span class="gl-text u-s-m-t-6">Pay Upon Cash on delivery. (This service is only available for some countries)</span>
                                                </div>
                                                <div class="u-s-m-b-10">

                                                    <!--====== Radio Box ======-->
                                                    <div class="radio-box">

                                                        <input type="radio" id="direct-bank-transfer" name="payment">
                                                        <div class="radio-box__state radio-box__state--primary">

                                                            <label class="radio-box__label" for="direct-bank-transfer">Direct
                                                                Bank Transfer</label></div>
                                                    </div>
                                                    <!--====== End - Radio Box ======-->

                                                    <span class="gl-text u-s-m-t-6">Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order will not be shipped until the funds have cleared in our account.</span>
                                                </div>
                                                <div class="u-s-m-b-10">

                                                    <!--====== Check Box ======-->
                                                    <div class="check-box">

                                                        <input type="checkbox" id="term-and-condition">
                                                        <div class="check-box__state check-box__state--primary">

                                                            <label class="check-box__label" for="term-and-condition">I
                                                                consent to the</label></div>
                                                    </div>
                                                    <!--====== End - Check Box ======-->

                                                    <a class="gl-link">Terms of Service.</a>
                                                </div>
                                                <div>

                                                    <button class="btn btn--e-brand-b-2" type="submit">PLACE ORDER
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <!--====== End - Order Summary ======-->
                            </div>
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

    <!--====== Modal Section ======-->


    <!--====== Shipping Address Add Modal ======-->
    <div class="modal fade" id="edit-ship-address">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="checkout-modal2">
                        <div class="u-s-m-b-30">
                            <div class="dash-l-r">
                                <h1 class="gl-modal-h1">Shipping Address</h1>
                                <div class="dash__link dash__link--brand">
                                    <a data-modal="modal" data-modal-id="#add-ship-address" data-dismiss="modal">Add new
                                        Address</a>
                                </div>
                            </div>
                        </div>
                        <form class="checkout-modal2__form">
                            <div class="dash__table-2-wrap u-s-m-b-30 gl-scroll">
                                <table class="dash__table-2">
                                    <thead>
                                    <tr>
                                        <th>Select</th>
                                        <th>Full Name</th>
                                        <th>Address</th>
                                        <th>Region</th>
                                        <th>Phone Number</th>
                                    </tr>
                                    </thead>
                                    <tbody id="shipping-addresses-body">
                                    <!-- Shipping addresses will be dynamically inserted here -->
                                    </tbody>
                                </table>
                            </div>
                            <div class="gl-modal-btn-group">
                                <button class="btn btn--e-brand-b-2" type="submit">SAVE</button>
                                <button class="btn btn--e-grey-b-2" type="button" data-dismiss="modal">CANCEL</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!--====== End - Shipping Address Add Modal ======-->


    <!--====== Shipping Address Add Modal ======-->
    <div class="modal fade" id="add-ship-address">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="checkout-modal1">
                        <!--                            <form class="checkout-modal1__form">-->
                        <!--                                <div class="u-s-m-b-30">-->
                        <!--                                    <h1 class="gl-modal-h1">Add new Shipping Address</h1>-->
                        <!--                                </div>-->
                        <!--                                <div class="gl-inline">-->
                        <!--                                    <div class="u-s-m-b-30">-->

                        <!--                                        <label class="gl-label" for="address-fname">FIRST NAME *</label>-->

                        <!--                                        <input class="input-text input-text&#45;&#45;primary-style" type="text" id="address-fname" placeholder="First Name"></div>-->
                        <!--                                    <div class="u-s-m-b-30">-->

                        <!--                                        <label class="gl-label" for="address-lname">LAST NAME *</label>-->

                        <!--                                        <input class="input-text input-text&#45;&#45;primary-style" type="text" id="address-lname" placeholder="Last Name"></div>-->
                        <!--                                </div>-->
                        <!--                                <div class="gl-inline">-->
                        <!--                                    <div class="u-s-m-b-30">-->

                        <!--                                        <label class="gl-label" for="address-phone">PHONE *</label>-->

                        <!--                                        <input class="input-text input-text&#45;&#45;primary-style" type="text" id="address-phone"></div>-->
                        <!--                                    <div class="u-s-m-b-30">-->

                        <!--                                        <label class="gl-label" for="address-street">STREET ADDRESS *</label>-->

                        <!--                                        <input class="input-text input-text&#45;&#45;primary-style" type="text" id="address-street" placeholder="House Name and Street"></div>-->
                        <!--                                </div>-->
                        <!--                                <div class="gl-inline">-->
                        <!--                                    <div class="u-s-m-b-30">-->

                        <!--                                        &lt;!&ndash;====== Select Box ======&ndash;&gt;-->

                        <!--&lt;!&ndash;                                        <label class="gl-label" for="country">COUNTRY *</label>&ndash;&gt;-->
                        <!--&lt;!&ndash;                                        <select class="select-box select-box&#45;&#45;primary-style"&ndash;&gt;-->
                        <!--&lt;!&ndash;                                                onchange="print_state('state',this.selectedIndex);"&ndash;&gt;-->
                        <!--&lt;!&ndash;                                                id="country" name="country">&ndash;&gt;-->
                        <!--&lt;!&ndash;                                            <option selected value="">Choose Country</option>&ndash;&gt;-->
                        <!--&lt;!&ndash;                                        </select>&ndash;&gt;-->
                        <!--                                        &lt;!&ndash;====== End - Select Box ======&ndash;&gt;-->
                        <!--                                    </div>-->
                        <!--                                    <div class="u-s-m-b-30">-->

                        <!--                                        &lt;!&ndash;====== Select Box ======&ndash;&gt;-->

                        <!--&lt;!&ndash;                                        <label class="gl-label" for="state">STATE/PROVINCE *</label>&ndash;&gt;-->
                        <!--&lt;!&ndash;                                        <select class="select-box select-box&#45;&#45;primary-style" name="state"&ndash;&gt;-->
                        <!--&lt;!&ndash;                                                id="state">&ndash;&gt;-->
                        <!--&lt;!&ndash;                                            <option selected value="">Choose State/Province</option>&ndash;&gt;-->
                        <!--&lt;!&ndash;                                        </select>&ndash;&gt;-->
                        <!--                                        &lt;!&ndash;====== End - Select Box ======&ndash;&gt;-->
                        <!--                                    </div>-->
                        <!--                                </div>-->
                        <!--                                <div class="gl-inline">-->
                        <!--                                    <div class="u-s-m-b-30">-->

                        <!--                                        <label class="gl-label" for="address-city">TOWN/CITY *</label>-->

                        <!--                                        <input class="input-text input-text&#45;&#45;primary-style" type="text" id="address-city"></div>-->
                        <!--                                    <div class="u-s-m-b-30">-->

                        <!--                                        <label class="gl-label" for="address-street">ZIP/POSTAL CODE *</label>-->

                        <!--                                        <input class="input-text input-text&#45;&#45;primary-style" type="text" id="address-postal" placeholder="Zip/Postal Code"></div>-->
                        <!--                                </div>-->
                        <!--                                <div class="gl-modal-btn-group">-->

                        <!--                                    <button class="btn btn&#45;&#45;e-brand-b-2" type="submit">SAVE</button>-->

                        <!--                                    <button class="btn btn&#45;&#45;e-grey-b-2" type="button" data-dismiss="modal">CANCEL</button></div>-->
                        <!--                            </form>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--====== End - Shipping Address Add Modal ======-->
    <!--====== End - Modal Section ======-->
</div>
<!--====== End - Main App ======-->


<!--====== Vendor Js ======-->
<script src="js/vendor.js"></script>

<!--====== jQuery Shopnav plugin ======-->
<script src="js/jquery.shopnav.js"></script>

<!--====== App ======-->
<script src="js/app.js"></script>

<!--====== Custom js ======-->
<script src="js/checkout.js"></script>
<script src="js/variousCountryListFormats.js"></script>
<script type="text/javascript" src="js/countries.js"></script>
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