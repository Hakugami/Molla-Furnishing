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
                                        <a href="dash-edit-profile.html">My Account</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="u-s-p-b-60">
                <div class="section__content">
                    <div class="dash">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 col-md-12">
                                    <div class="dash__box dash__box--bg-white dash__box--shadow u-s-m-b-30">
                                        <div class="dash__pad-1">
                                            <span class="dash__text u-s-m-b-16"></span>
                                            <ul class="dash__f-list">
                                                <li>

                                                    <a class="dash-active" href="profile">Manage My Account</a></li>
                                                <li>

                                                    <a href="myProfile">My Profile</a></li>
                                                <li>

                                                    <a href="address">Address Book</a></li>
                                                <li>

                                                    <a href="dash-track-order.html">Track Order</a></li>
                                                <li>

                                                    <a href="dash-my-order.html">My Orders</a></li>
                                                <li>

                                                    <a href="dash-payment-option.html">My Payment Options</a></li>
                                                <li>

                                                    <a href="dash-cancellation.html">My Returns & Cancellations</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="dash__box dash__box--bg-white dash__box--shadow dash__box--w">
                                        <div class="dash__pad-1">
                                            <ul class="dash__w-list">
                                                <li>
                                                    <div class="dash__w-wrap">

                                                        <span class="dash__w-icon dash__w-icon-style-1"><i class="fas fa-cart-arrow-down"></i></span>

                                                        <span class="dash__w-text">4</span>

                                                        <span class="dash__w-name">Orders Placed</span></div>
                                                </li>
                                                <li>
                                                    <div class="dash__w-wrap">

                                                        <span class="dash__w-icon dash__w-icon-style-2"><i class="fas fa-times"></i></span>

                                                        <span class="dash__w-text">0</span>

                                                        <span class="dash__w-name">Cancel Orders</span></div>
                                                </li>
                                                <li>
                                                    <div class="dash__w-wrap">

                                                        <span class="dash__w-icon dash__w-icon-style-3"><i class="far fa-heart"></i></span>

                                                        <span class="dash__w-text">0</span>

                                                        <span class="dash__w-name">Wishlist</span></div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!--====== End - Dashboard Features ======-->
                                </div>
                                <div class="col-lg-9 col-md-12">
                                    <div class="dash__box dash__box--shadow dash__box--radius dash__box--bg-white">
                                        <div class="dash__pad-2">
                                            <h1 class="dash__h1 u-s-m-b-14">Edit Profile</h1>

                                            <span class="dash__text u-s-m-b-30">Looks like you haven't update your profile</span>
                                            <div class="dash__link dash__link--secondary u-s-m-b-30">

                                                <a data-modal="modal" data-modal-id="#dash-newsletter">Subscribe Newsletter</a></div>
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <form class="dash-edit-p">
                                                        <div class="gl-inline">
                                                            <div class="u-s-m-b-30">

                                                                <label class="gl-label" for="Ureg-fname">USER NAME *</label>

                                                                <input class="input-text input-text--primary-style" type="text" id="Ureg-fname" placeholder=""></div>
                                                            <div class="u-s-m-b-30">
                                                                <label class="gl-label" for="reg-interests">INTERESTS *</label>
                                                                <select class="select-box select-box--primary-style" id="reg-interests" required>
                                                                    <option value=""  disabled selected>Select Interests</option>
                                                                    <option value="chairs">Chairs</option>
                                                                    <option value="tables">Tables</option>
                                                                    <option value="storage">Storage</option>
                                                                    <option value="beds">Beds</option>
                                                                    <option value="desks">Desks</option>
                                                                    <option value="dining">Dining</option>
                                                                    <option value="children">Children</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="gl-inline">

                                                            <div class="u-s-m-b-30">

                                                                <!--====== Date of Birth Select-Box ======-->

                                                                <span class="gl-label">BIRTHDAY</span>
                                                                <div class="gl-dob">
                                                                    <select class="select-box select-box--primary-style" id="Umonth">
                                                                        <option disabled selected value="">Month</option>
                                                                        <!-- Month options will be generated by JavaScript -->
                                                                    </select>
                                                                    <select class="select-box select-box--primary-style" id="Uday">
                                                                        <option disabled selected value="">Day</option>
                                                                        <!-- Day options will be generated by JavaScript -->
                                                                    </select>
                                                                    <select class="select-box select-box--primary-style" id="Uyear">
                                                                        <option disabled selected value="">Year</option>
                                                                        <!-- Year options will be generated by JavaScript -->
                                                                    </select>
                                                                </div>
                                                                <!--====== End - Date of Birth Select-Box ======-->
                                                            </div>
                                                            <div class="u-s-m-b-30">

                                                                <label class="gl-label" for="Ugender">GENDER</label><select class="select-box select-box--primary-style u-w-100" id="Ugender">
                                                                    <option disabled selected value="">Select</option>
                                                                    <option value="male">Male</option>
                                                                    <option value="male">Female</option>
                                                                </select></div>
                                                        </div>
<!--                                                        <div class="gl-inline">-->
<!--                                                            <div class="u-s-m-b-30">-->
<!--                                                                <h2 class="dash__h2 u-s-m-b-8">E-mail</h2>-->

<!--                                                                <span class="dash__text">johndoe@domain.com</span>-->
<!--                                                                <div class="dash__link dash__link&#45;&#45;secondary">-->

<!--                                                                    <a href="#">Change</a></div>-->
<!--                                                            </div>-->
<!--                                                            <div class="u-s-m-b-30">-->
<!--                                                                <h2 class="dash__h2 u-s-m-b-8">Phone</h2>-->

<!--                                                                <span class="dash__text">Please enter your mobile</span>-->
<!--                                                                <div class="dash__link dash__link&#45;&#45;secondary">-->

<!--                                                                    <a href="#">Add</a></div>-->
<!--                                                            </div>-->
<!--                                                        </div>-->

                                                        <button id="update-profile-btn" class="btn btn--e-brand-b-2" type="submit">SAVE</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
           <jsp:include page="/footer.jsp" />
    </div>

    <!--====== Vendor Js ======-->
    <script src="js/vendor.js"></script>

    <!--====== jQuery Shopnav plugin ======-->
    <script src="js/jquery.shopnav.js"></script>

    <!--====== App ======-->
    <script src="js/app.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>

    <!--====== Custom js ======-->
    <script src="js/update-profile.js"></script>


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