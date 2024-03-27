<%@page session="false" contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/header.jsp"/>

<!--====== App Content ======-->
<div class="app-content">

    <!--====== Section 1 ======-->
    <div class="u-s-p-t-90">
        <div class="container">
            <div class="row">
                <div class="col-lg-5">

                    <!--====== Product Breadcrumb ======-->
                    <div class="pd-breadcrumb u-s-m-b-30">
                        <ul class="pd-breadcrumb__list">
                            <li class="has-separator">
                                <a id="productCategory" href=""></a>
                            </li>
                            <li class="has-separator">
                                <a id="productSubcategory" href=""></a>
                            </li>
                            <li class="is-marked">
                                <a id="productName" href=""></a>
                            </li>
                        </ul>
                    </div>
                    <!--====== End - Product Breadcrumb ======-->


                    <!--====== Product Detail Zoom ======-->
                    <div class="pd u-s-m-b-30">
                        <div class="slider-fouc pd-wrap">
                            <div id="pd-o-initiate">
                                <div id="img-1-wrap" class="main-image pd-o-img-wrap" data-src="">
                                    <img id="img-1" class="sec-image u-img-fluid" src="" data-zoom-image="" alt="">
                                </div>
                                <div id="img-2-wrap" class="pd-o-img-wrap" data-src="">
                                    <img id="img-2" class="u-img-fluid" src="" data-zoom-image="" alt="">
                                </div>
                                <div id="img-3-wrap" class="pd-o-img-wrap" data-src="">
                                    <img id="img-3" class="u-img-fluid" src="" data-zoom-image="" alt="">
                                </div>
                                <div id="img-4-wrap" class="pd-o-img-wrap" data-src="">
                                    <img id="img-4" class="u-img-fluid" src="" data-zoom-image="" alt="">
                                </div>
                                <div id="img-5-wrap" class="pd-o-img-wrap" data-src="">
                                    <img id="img-5" class="u-img-fluid" src="" data-zoom-image="" alt="">
                                </div>

                            </div>


                            <span class="pd-text">Click for larger zoom</span>
                        </div>
                        <div class="u-s-m-t-15">
                            <div class="slider-fouc">
                                <div id="pd-o-thumbnail">
                                    <div>
                                        <img id="thumb-1" class="thumb-image u-img-fluid" src="" alt=""></div>
                                    <div>

                                        <img id="thumb-2" class="u-img-fluid" src="" alt=""></div>
                                    <div>

                                        <img id="thumb-3" class="u-img-fluid" src="" alt=""></div>
                                    <div>

                                        <img id="thumb-4" class="u-img-fluid" src="" alt=""></div>
                                    <div>

                                        <img id="thumb-5" class="u-img-fluid" src="" alt=""></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--====== End - Product Detail Zoom ======-->
                </div>
                <div class="col-lg-7">

                    <!--====== Product Right Side Details ======-->
                    <div class="pd-detail">
                        <div>

                            <span class="pd-detail__name">Nikon Camera 4k Lens Zoom Pro</span></div>
                        <div>
                            <div class="pd-detail__inline">

                                <span class="pd-detail__price">$6.99</span>

                                <span class="pd-detail__discount">(76% OFF)</span><del class="pd-detail__del">$28.97</del></div>
                        </div>
                        <div class="u-s-m-b-15">
                            <div class="pd-detail__rating gl-rating-style"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i>

                                <span class="pd-detail__review u-s-m-l-4">

                                            <a data-click-scroll="#view-review">23 Reviews</a></span></div>
                        </div>
                        <div class="u-s-m-b-15">
                            <div class="pd-detail__inline">

                                <span class="pd-detail__stock">200 in stock</span>

                            </div>
                            <div class="u-s-m-b-15">

                                <span id="description2" class="pd-detail__preview-desc"></span></div>

                            <div class="u-s-m-b-15">
                                <form class="pd-detail__form">
                                    <div class="pd-detail-inline-2">
                                        <div class="u-s-m-b-15">

                                            <!--====== Input Counter ======-->
                                            <div class="input-counter">

                                                <span class="input-counter__minus fas fa-minus"></span>

                                                <input class="input-counter__text input-counter--text-primary-style" type="text" value="1" data-min="1" data-max="1000">

                                                <span class="input-counter__plus fas fa-plus"></span></div>
                                            <!--====== End - Input Counter ======-->
                                        </div>
                                        <div class="u-s-m-b-15">

                                            <button class="btn btn--e-brand-b-2" type="submit">Add to Cart</button></div>
                                    </div>
                                </form>
                            </div>
                            <div class="u-s-m-b-15">

                                <span class="pd-detail__label u-s-m-b-8">Product Policy:</span>
                                <ul class="pd-detail__policy-list">
                                    <li><i class="fas fa-check-circle u-s-m-r-8"></i>

                                        <span>Buyer Protection.</span></li>
                                    <li><i class="fas fa-check-circle u-s-m-r-8"></i>

                                        <span>Full Refund if you don't receive your order.</span></li>
                                    <li><i class="fas fa-check-circle u-s-m-r-8"></i>

                                        <span>Returns accepted if product not as described.</span></li>
                                </ul>
                            </div>
                        </div>
                        <!--====== End - Product Right Side Details ======-->
                    </div>
                </div>
            </div>
        </div>

        <!--====== Product Detail Tab ======-->
        <div class="u-s-p-y-90">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="pd-tab">
                            <div class="u-s-m-b-30">
                                <ul class="nav pd-tab__list">
                                    <li class="nav-item">

                                        <a class="nav-link active" data-toggle="tab" href="#pd-desc">DESCRIPTION</a></li>
                                </ul>
                            </div>
                            <div class="tab-content">

                                <!--====== Tab 1 ======-->
                                <div class="tab-pane fade show active" id="pd-desc">
                                    <div class="pd-tab__desc">
                                        <div class="u-s-m-b-15">
                                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                                        </div>
                                        <div class="u-s-m-b-30"><iframe src="" allowfullscreen></iframe></div>
                                        <div class="u-s-m-b-30">
                                            <ul>
                                                <li><i class="fas fa-check u-s-m-r-8"></i>

                                                    <span>Buyer Protection.</span></li>
                                                <li><i class="fas fa-check u-s-m-r-8"></i>

                                                    <span>Full Refund if you don't receive your order.</span></li>
                                                <li><i class="fas fa-check u-s-m-r-8"></i>

                                                    <span>Returns accepted if product not as described.</span></li>
                                            </ul>
                                        </div>
                                        <div class="u-s-m-b-15">
                                            <h4>PRODUCT INFORMATION</h4>
                                        </div>
                                        <div class="u-s-m-b-15">
                                            <div class="pd-table gl-scroll">
                                                <table>
                                                    <tbody>
                                                    <tr>
                                                        <td>Main Material</td>
                                                        <td id="product-material"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Color</td>
                                                        <td id="product-color">/td>
                                                    </tr>
                                                    <tr>
                                                        <td>Dimension</td>
                                                        <td id="product-dimensions"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Shipping Weight (kg)</td>
                                                        <td id="product-weight">0.5</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--====== End - Tab 1 ======-->


                                <!--====== Tab 2 ======-->

                                <!--====== End - Tab 2 ======-->


                                <!--====== Tab 3 ======-->
                                <!--====== End - Tab 3 ======-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--====== End - Product Detail Tab ======-->
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
                                                               title="Add to Cart"><i class="fas fa-plus-circle"></i></a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>

                                            <span class="product-o__category">

                                                       <a href="product?category=${product.categoryName}">${product.categoryName}</a></span>

                                            <span class="product-o__name">

                                                       <a href="ProductPage?name=${product.name}">${product.name}</a></span>
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
    </div>
    <!--====== End - App Content ======-->


    <!--====== Main Footer ======-->
    <jsp:include page="/footer.jsp"/>

    <!--====== Modal Section ======-->


    <!--====== Quick Look Modal ======-->
    <div class="modal fade" id="quick-look">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content modal--shadow">

                <button class="btn dismiss-button fas fa-times" type="button" data-dismiss="modal"></button>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-5">

                            <!--====== Product Breadcrumb ======-->
                            <div class="pd-breadcrumb u-s-m-b-30">
                                <ul class="pd-breadcrumb__list">
                                    <li class="has-separator">

                                        <a href="index.hml">Home</a></li>
                                    <li class="has-separator">

                                        <a href="shop-side-version-2.html">Electronics</a></li>
                                    <li class="has-separator">

                                        <a href="shop-side-version-2.html">DSLR Cameras</a></li>
                                    <li class="is-marked">

                                        <a href="shop-side-version-2.html">Nikon Cameras</a></li>
                                </ul>
                            </div>
                            <!--====== End - Product Breadcrumb ======-->


                            <!--====== Product Detail ======-->
                            <div class="pd u-s-m-b-30">
                                <div class="pd-wrap">
                                    <div id="js-product-detail-modal">
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-1.jpg" alt=""></div>
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-2.jpg" alt=""></div>
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-3.jpg" alt=""></div>
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-4.jpg" alt=""></div>
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-5.jpg" alt=""></div>
                                    </div>
                                </div>
                                <div class="u-s-m-t-15">
                                    <div id="js-product-detail-modal-thumbnail">
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-1.jpg" alt=""></div>
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-2.jpg" alt=""></div>
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-3.jpg" alt=""></div>
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-4.jpg" alt=""></div>
                                        <div>

                                            <img class="u-img-fluid" src="images/product/product-d-5.jpg" alt=""></div>
                                    </div>
                                </div>
                            </div>
                            <!--====== End - Product Detail ======-->
                        </div>
                        <div class="col-lg-7">

                            <!--====== Product Right Side Details ======-->
                            <div class="pd-detail">
                                <div>

                                    <span class="pd-detail__name">Nikon Camera 4k Lens Zoom Pro</span></div>
                                <div>
                                    <div class="pd-detail__inline">

                                        <span class="pd-detail__price">$6.99</span>

                                        <span class="pd-detail__discount">(76% OFF)</span><del class="pd-detail__del">$28.97</del></div>
                                </div>
                                <div class="u-s-m-b-15">
                                    <div class="pd-detail__rating gl-rating-style"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i>

                                        <span class="pd-detail__review u-s-m-l-4">

                                                <a href="product-detail.html">23 Reviews</a></span></div>
                                </div>
                                <div class="u-s-m-b-15">
                                    <div class="pd-detail__inline">

                                        <span class="pd-detail__stock"></span>
                                    </div>
                                    <div class="u-s-m-b-15">

                                        <span class="pd-detail__preview-desc">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</span></div>
                                    <div class="u-s-m-b-15">
                                        <div class="pd-detail__inline">

                                            <span class="pd-detail__click-wrap"><i class="far fa-heart u-s-m-r-6"></i>

                                                <a href="signin.html">Add to Wishlist</a>

                                                <span class="pd-detail__click-count">(222)</span></span></div>
                                    </div>
                                    <div class="u-s-m-b-15">
                                        <form class="pd-detail__form">
                                            <div class="pd-detail-inline-2">
                                                <div class="u-s-m-b-15">

                                                    <!--====== Input Counter ======-->
                                                    <div class="input-counter">

                                                        <span class="input-counter__minus fas fa-minus"></span>

                                                        <input class="input-counter__text input-counter--text-primary-style" type="text" value="1" data-min="1" data-max="1000">

                                                        <span class="input-counter__plus fas fa-plus"></span></div>
                                                    <!--====== End - Input Counter ======-->
                                                </div>
                                                <div class="u-s-m-b-15">

                                                    <button class="btn btn--e-brand-b-2" type="submit">Add to Cart</button></div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="u-s-m-b-15">

                                        <span class="pd-detail__label u-s-m-b-8">Product Policy:</span>
                                        <ul class="pd-detail__policy-list">
                                            <li><i class="fas fa-check-circle u-s-m-r-8"></i>

                                                <span>Buyer Protection.</span></li>
                                            <li><i class="fas fa-check-circle u-s-m-r-8"></i>

                                                <span>Full Refund if you don't receive your order.</span></li>
                                            <li><i class="fas fa-check-circle u-s-m-r-8"></i>

                                                <span>Returns accepted if product not as described.</span></li>
                                        </ul>
                                    </div>
                                </div>
                                <!--====== End - Product Right Side Details ======-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--====== End - Quick Look Modal ======-->


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

                                        <span>Item is added successfully!</span></div>
                                    <div class="success__img-wrap">

                                        <img class="u-img-fluid" src="images/product/electronic/product1.jpg" alt=""></div>
                                    <div class="success__info-wrap">

                                        <span class="success__name">Beats Bomb Wireless Headphone</span>

                                        <span class="success__quantity">Quantity: 1</span>

                                        <span class="success__price">$170.00</span></div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12">
                                <div class="s-option">

                                    <span class="s-option__text">1 item (s) in your cart</span>
                                    <div class="s-option__link-box">

                                        <a class="s-option__link btn--e-white-brand-shadow" data-dismiss="modal">CONTINUE SHOPPING</a>

                                        <a class="s-option__link btn--e-white-brand-shadow" href="cart">VIEW CART</a>

                                        <a class="s-option__link btn--e-brand-shadow" href="checkout">PROCEED TO CHECKOUT</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--====== End - Add to Cart Modal ======-->
        <!--====== End - Modal Section ======-->
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
    <script src="js/sessionStorageUtils.js"></script>
    <script src="js/product-details.js"></script>


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