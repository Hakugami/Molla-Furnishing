<%@page session="false" contentType="text/html" pageEncoding="utf-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <jsp:include page="/header.jsp" />

        <!--====== App Content ======-->
        <div class="app-content">

            <!--====== Primary Slider ======-->
            <div class="s-skeleton s-skeleton--h-640 s-skeleton--bg-grey">
                <div class="owl-carousel primary-style-3" id="hero-slider">
                    <div class="hero-slide hero-slide--7">
                        <div class="primary-style-3-wrap">
                            <div class="container">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="slider-content slider-content--animation">

                                            <span class="content-span-1 u-c-white">Update Your Fashion</span>

                                            <span class="content-span-2 u-c-white">10% Discount on Outwear</span>

                                            <span class="content-span-3 u-c-white">Find outwear on best prices</span>

                                            <span class="content-span-4 u-c-white">Starting At

                                                <span class="u-c-brand">$100.00</span></span>

                                            <a class="shop-now-link btn--e-brand" href="shop-side-version-2.html">SHOP
                                                NOW</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="hero-slide hero-slide--8">
                        <div class="primary-style-3-wrap">
                            <div class="container">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="slider-content slider-content--animation">

                                            <span class="content-span-1 u-c-white">Open Your Eyes</span>

                                            <span class="content-span-2 u-c-white">10% Off On Intimates</span>

                                            <span class="content-span-3 u-c-white">Find intimates on best prices</span>

                                            <span class="content-span-4 u-c-white">Starting At

                                                <span class="u-c-brand">$100.00</span></span>

                                            <a class="shop-now-link btn--e-brand" href="shop-side-version-2.html">SHOP
                                                NOW</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="hero-slide hero-slide--9">
                        <div class="primary-style-3-wrap">
                            <div class="container">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="slider-content slider-content--animation">

                                            <span class="content-span-1 u-c-white">Find Top Brands</span>

                                            <span class="content-span-2 u-c-white">10% Off On Outwear</span>

                                            <span class="content-span-3 u-c-white">Find outwear on best prices</span>

                                            <span class="content-span-4 u-c-white">Starting At

                                                <span class="u-c-brand">$100.00</span></span>

                                            <a class="shop-now-link btn--e-brand" href="shop-side-version-2.html">SHOP
                                                NOW</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Primary Slider ======-->


            <!--====== Section 1 ======-->
            <div class="u-s-p-y-60">

                <!--====== Section Content ======-->
                <div class="section__content">
                    <div class="container">
                        <style>
                            .horizontal-scroll-wrapper {
                                display: flex;
                                flex-wrap: nowrap;
                                overflow-x: auto;
                                -webkit-overflow-scrolling: touch; /* For smooth scrolling on iOS */
                                scrollbar-width: none; /* Hides scrollbar for Firefox */
                                -ms-overflow-style: none; /* Hides scrollbar for Internet Explorer */
                            }
            
                            .horizontal-scroll-wrapper::-webkit-scrollbar {
                                display: none; /* Hides scrollbar for Chrome, Safari, and Edge */
                            }
                        </style>
                        <div class="row horizontal-scroll-wrapper">
                            <div class="col-lg-3 col-md-3 col-sm-6 u-s-m-b-30">
                                <div class="promotion-o">
                                    <div class="aspect aspect--bg-grey aspect--square">

                                        <img class="aspect__img" src="images/storage.webp" alt="">
                                    </div>
                                    <div class="promotion-o__content">

                                        <a class="promotion-o__link btn--e-white-brand"
                                            href="product?category=storage">Storage</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-6 u-s-m-b-30">
                                <div class="promotion-o">
                                    <div class="aspect aspect--bg-grey aspect--square">

                                        <img class="aspect__img" src="images/chairs.webp" alt="">
                                    </div>
                                    <div class="promotion-o__content">

                                        <a class="promotion-o__link btn--e-white-brand"
                                            href="product?category=chairs">Chairs</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-6 u-s-m-b-30">
                                <div class="promotion-o">
                                    <div class="aspect aspect--bg-grey aspect--square">

                                        <img class="aspect__img" src="images/desks.jpg" alt="">
                                    </div>
                                    <div class="promotion-o__content">

                                        <a class="promotion-o__link btn--e-white-brand"
                                            href="product?category=desks">Desks</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-6 u-s-m-b-30">
                                <div class="promotion-o">
                                    <div class="aspect aspect--bg-grey aspect--square">

                                        <img class="aspect__img" src="images/tables.jpg" alt="">
                                    </div>
                                    <div class="promotion-o__content">

                                        <a class="promotion-o__link btn--e-white-brand"
                                            href="product?category=tables">Tables</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-6 u-s-m-b-30">
                                <div class="promotion-o">
                                    <div class="aspect aspect--bg-grey aspect--square">

                                        <img class="aspect__img" src="images/children.jpg" alt="">
                                    </div>
                                    <div class="promotion-o__content">

                                        <a class="promotion-o__link btn--e-white-brand"
                                            href="product?category=children">Childrens</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-6 u-s-m-b-30">
                                <div class="promotion-o">
                                    <div class="aspect aspect--bg-grey aspect--square">

                                        <img class="aspect__img" src="images/beds.webp" alt="">
                                    </div>
                                    <div class="promotion-o__content">

                                        <a class="promotion-o__link btn--e-white-brand"
                                            href="product?category=beds">Beds</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-6 u-s-m-b-30">
                                <div class="promotion-o">
                                    <div class="aspect aspect--bg-grey aspect--square">

                                        <img class="aspect__img" src="images/dining.jpg" alt="">
                                    </div>
                                    <div class="promotion-o__content">

                                        <a class="promotion-o__link btn--e-white-brand"
                                            href="product?category=dining">Dining</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--====== End - Section Content ======-->
            </div>
            <!--====== End - Section 1 ======-->

            <!--====== Section 3 ======-->
            <div class="u-s-p-b-60">

                <!--====== Section Intro ======-->
                <div class="section__intro u-s-m-b-46">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="section__text-wrap">
                                    <h1 class="section__heading u-c-secondary u-s-m-b-12">RECENT PRODUCTS</h1>

                                    <span class="section__span u-c-silver">NEWLY ADDED PRODUCTS</span>
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
                            <c:forEach items="${recentProducts}" var="product">

                                <div class="col-lg-3 col-md-4 col-sm-6 u-s-m-b-30">
                                    <div class="product-r u-h-100">
                                        <div class="product-r__container">

                                            <a class="aspect aspect--bg-grey aspect--square u-d-block"
                                                href="ProductPage?name=${product.name}">

                                                <img class="aspect__img" src="${product.images[1]}" alt=""></a>
                                            <div class="product-r__action-wrap">
                                                <ul class="product-r__action-list">

                                                    <li>
                                                        <a data-modal="modal" data-modal-id="#add-to-cart"><i
                                                                class="fas fa-plus-circle"></i></a>
                                                    </li>

                                                </ul>
                                            </div>
                                        </div>
                                        <div class="product-r__info-wrap">

                                            <span class="product-r__category">

                                                <a
                                                    href="product?category=${product.categoryName}">${product.categoryName}</a></span>
                                            <div class="product-r__n-p-wrap">

                                                <span class="product-r__name">

                                                    <a class="product-name"
                                                        href="ProductPage?name=${product.name}">${product.name}</a></span>

                                                <span class="product-r__price">${product.price}</span>
                                            </div>

                                            <span class="product-r__description">${product.description}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
                <!--====== End - Section Content ======-->
            </div>
            <!--====== End - Section 3 ======-->

            <c:if test="${not empty personalizedProducts}">
                <!--====== Section 4 ======-->
                <div class="u-s-p-b-60">

                    <!--====== Section Intro ======-->
                    <div class="section__intro u-s-m-b-46">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="section__text-wrap">
                                        <h1 class="section__heading u-c-secondary u-s-m-b-12">JUST FOR YOU</h1>

                                        <span class="section__span u-c-silver">DISCOVER WHAT IS PERFECT FOR YOU</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--====== End - Section Intro ======-->


                    <!--====== Section Content ======-->
                    <div class="section__content">
                        <div class="container">
                            <div class="slider-fouc">
                                <div class="owl-carousel product-slider" data-item="4">
                                    <c:forEach items="${personalizedProducts}" var="product">
                                        <div class="u-s-m-b-30">
                                            <div class="product-o product-o--hover-on">
                                                <div class="product-o__wrap">

                                                    <a class="aspect aspect--bg-grey aspect--square u-d-block"
                                                        href="ProductPage?name=${product.name}">

                                                        <img class="aspect__img" src="${product.images[0]}" alt=""></a>
                                                    <div class="product-o__action-wrap">
                                                        <ul class="product-o__action-list">
                                                            <li>

                                                                <a data-modal="modal" data-modal-id="#add-to-cart"
                                                                    data-tooltip="tooltip" data-placement="top"
                                                                    title="Add to Cart"><i
                                                                        class="fas fa-plus-circle"></i></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>

                                                <span class="product-o__category">

                                                    <a
                                                        href="product?category=${product.categoryName}">${product.categoryName}</a></span>

                                                <span class="product-o__name">

                                                    <a
                                                        href="ProductPage?name=${product.name}">${product.name}</a></span>
                                                <div class="product-o__rating gl-rating-style">

                                                </div>

                                                <span class="product-o__price">${product.price}</span>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--====== End - Section Content ======-->
                </div>
                <!--====== End - Section 4 ======-->
            </c:if>


            <jsp:include page="/footer.jsp" />


            <!--====== Modal Section ======-->


            <!--====== Add to Cart Modal ======-->
            <div class="modal fade" id="add-to-cart">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content modal-radius modal-shadow">

                        <button class="btn dismiss-button fas fa-times" type="button" data-dismiss="modal"></button>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-lg-6 col-md-12">
                                    <div class="success u-s-m-b-30">
                                        <div class="success__text-wrap"><i class="fas fa-check"></i>

                                            <span>Item is added successfully!</span>
                                        </div>
                                        <div class="success__img-wrap">

                                            <img class="u-img-fluid" src="images/product/electronic/product1.jpg"
                                                alt="">
                                        </div>
                                        <div class="success__info-wrap">

                                            <%-- get the product details and attach it in the modal via jstl--%>
                                                <span class="success__name">Beats Bomb Wireless Headphone</span>

                                                <span class="success__quantity">Quantity: 1</span>

                                                <span class="success__price">$170.00</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-12">
                                    <div class="s-option">

                                        <span class="s-option__text">1 item (s) in your cart</span>
                                        <div class="s-option__link-box">

                                            <a class="s-option__link btn--e-white-brand-shadow"
                                                data-dismiss="modal">CONTINUE
                                                SHOPPING</a>

                                            <a class="s-option__link btn--e-white-brand-shadow" href="cart">VIEW
                                                CART</a>

                                            <a class="s-option__link btn--e-brand-shadow" href="checkout">PROCEED TO
                                                CHECKOUT</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Add to Cart Modal ======-->


            <!--====== Newsletter Subscribe Modal ======-->
            <div class="modal fade new-l" id="newsletter-modal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content modal--shadow">

                        <button class="btn new-l__dismiss fas fa-times" type="button" data-dismiss="modal"></button>
                        <div class="modal-body">
                            <div class="row u-s-m-x-0">
                                <div class="col-lg-6 new-l__col-1 u-s-p-x-0">

                                    <a class="new-l__img-wrap u-d-block" href="shop-side-version-2.html">

                                        <img class="u-img-fluid u-d-block" src="images/newsletter/newsletter.jpg"
                                            alt=""></a>
                                </div>
                                <div class="col-lg-6 new-l__col-2">
                                    <div class="new-l__section u-s-m-t-30">
                                        <div class="u-s-m-b-8 new-l--center">
                                            <h3 class="new-l__h3">Newsletter</h3>
                                        </div>
                                        <div class="u-s-m-b-30 new-l--center">
                                            <p class="new-l__p1">Sign up for emails to get the scoop on new arrivals,
                                                special
                                                sales and more.</p>
                                        </div>
                                        <form class="new-l__form">
                                            <div class="u-s-m-b-15">

                                                <input class="news-l__input" type="text" placeholder="E-mail Address">
                                            </div>
                                            <div class="u-s-m-b-15">

                                                <button class="btn btn--e-brand-b-2" type="submit">Sign up!</button>
                                            </div>
                                        </form>
                                        <div class="u-s-m-b-15 new-l--center">
                                            <p class="new-l__p2">By Signing up, you agree to receive Reshop
                                                offers,<br />promotions
                                                and other commercial messages. You may unsubscribe at any time.</p>
                                        </div>
                                        <div class="u-s-m-b-15 new-l--center">

                                            <a class="new-l__link" data-dismiss="modal">No Thanks</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--====== End - Newsletter Subscribe Modal ======-->
            <!--====== End - Modal Section ======-->
        </div>
        <!--====== End - Main App ======-->

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

                                <span class="app-setting__text">Please enable JavaScript in your browser or upgrade to a
                                    JavaScript-capable browser.</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </noscript>
        </body>

        </html>