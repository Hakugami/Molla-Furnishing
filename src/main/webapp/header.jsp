<%@page session="false" contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html class="no-js" lang="en">
<head>
    <!--====== Vendor Js ======-->
    <script src="js/vendor.js"></script>

    <!--====== jQuery Shopnav plugin ======-->
    <script src="js/jquery.shopnav.js"></script>
    <meta charset="UTF-8">
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="images/favicon.png" rel="shortcut icon">
    <title>Ludus - Electronics, Apparel, Computers, Books, DVDs & more</title>

    <!--====== Google Font ======-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800" rel="stylesheet">

    <!--====== Vendor Css ======-->
    <link rel="stylesheet" href="css/vendor.css">

    <!--====== Utility-Spacing ======-->
    <link rel="stylesheet" href="css/utility.css">

    <!--====== App ======-->
    <link rel="stylesheet" href="css/app.css">
</head>
<body class="config">
<div class="preloader is-active">
    <div class="preloader__wrap">

        <img class="preloader__img" src="images/preloader.png" alt=""></div>
</div>

<!--====== Main App ======-->
<div id="app">

    <!--====== Main Header ======-->
    <header class="header--style-1 header--box-shadow">

        <!--====== Nav 1 ======-->
        <nav class="primary-nav primary-nav-wrapper--border">
            <div class="container">

                <!--====== Primary Nav ======-->
                <div class="primary-nav">

                    <!--====== Main Logo ======-->

                    <a class="main-logo" href="home">

                        <img src="images/logo/logo-3.png" alt=""></a>
                    <!--====== End - Main Logo ======-->


                    <!--====== Search Form ======-->
                    <form class="main-form">

                        <label for="main-search"></label>

                        <input class="input-text input-text--border-radius input-text--style-1" type="text"
                               id="main-search" placeholder="Search">

                        <button class="btn btn--icon fas fa-search main-search-button" type="submit"></button></form>
                    <!--====== End - Search Form ======-->


                    <!--====== Dropdown Main plugin ======-->
                    <div class="menu-init" id="navigation">

                        <button class="btn btn--icon toggle-button fas fa-cogs" type="button"></button>

                        <!--====== Menu ======-->
                        <div class="ah-lg-mode">

                            <span class="ah-close">✕ Close</span>
                            <c:choose>
                                <c:when test="${not empty name}">

                                    <!--====== List (Logged-in User) ======-->
                                    <ul class="ah-list ah-list--design1 ah-list--link-color-secondary">
                                        <li class="has-dropdown" data-tooltip="tooltip" data-placement="left"
                                            title="${name}">

                                            <a><i class="far fa-user-circle"></i></a>


                                            <!--====== Dropdown ======-->

                                            <span class="js-menu-toggle"></span>
                                            <ul style="width:120px">

                                                <li>

                                                    <a href="profile"><i class="fas fa-user-circle u-s-m-r-6"></i>

                                                        <span>Account</span></a></li>

                                                <li>

                                                    <a href="logout"><i class="fas fa-lock-open u-s-m-r-6"></i>

                                                        <span>Signout</span></a></li>
                                            </ul>
                                            <!--====== End - Dropdown ======-->
                                        </li>

                                    </ul>
                                    <!--====== End - List (Logged-in User) ======-->

                                </c:when>

                                <c:otherwise>

                                    <!--====== List (Non-Logged-in User) ======-->
                                    <ul class="ah-list ah-list--design1 ah-list--link-color-secondary">
                                        <li class="has-dropdown" data-tooltip="tooltip" data-placement="left">

                                            <a><i class="far fa-user-circle"></i></a>


                                            <!--====== Dropdown ======-->

                                            <span class="js-menu-toggle"></span>
                                            <ul style="width:120px">

                                                <li>

                                                    <a href="register"><i class="fas fa-user-plus u-s-m-r-6"></i>

                                                        <span>Signup</span></a></li>
                                                <li>

                                                    <a href="login"><i class="fas fa-lock u-s-m-r-6"></i>

                                                        <span>Signin</span></a></li>

                                            </ul>
                                            <!--====== End - Dropdown ======-->
                                        </li>

                                    </ul>
                                    <!--====== End - List( Non-Logged-in User) ======-->

                                </c:otherwise>
                            </c:choose>

                        </div>
                        <!--====== End - Menu ======-->
                    </div>
                    <!--====== End - Dropdown Main plugin ======-->
                </div>
                <!--====== End - Primary Nav ======-->
            </div>
        </nav>
        <!--====== End - Nav 1 ======-->


        <!--====== Nav 2 ======-->
        <nav class="secondary-nav-wrapper">
            <div class="container">

                <!--====== Secondary Nav ======-->
                <div class="secondary-nav">

                    <!--====== Dropdown Main plugin ======-->
                    <div class="menu-init" id="navigation1">

                        <button class="btn btn--icon toggle-mega-text toggle-button" type="button">M</button>

                        <!--====== Menu ======-->
                        <div class="ah-lg-mode">

                            <span class="ah-close">✕ Close</span>

                            <!--====== List ======-->
                            <ul class="ah-list">
                                <li class="has-dropdown">

                                    <span class="mega-text">M</span>

                                    <!--====== Mega Menu ======-->

                                    <span class="js-menu-toggle"></span>
                                    <div class="mega-menu">
                                        <div class="mega-menu-wrap">
                                            <div class="mega-menu-list">
                                                <ul>
                                                    <li class="js-active">
                                                        <a href="shop-side-version-2.html">
                                                            <i class="fas fa-couch u-s-m-r-6"></i>
                                                            <span>Molla Furniture</span>
                                                        </a>
                                                        <span class="js-menu-toggle js-toggle-mark"></span>
                                                    </li>
                                                </ul>
                                            </div>


                                            <!--====== Furniture & Decor ======-->
                                            <div class="mega-menu-content js-active">

                                                <!--====== Mega Menu Row ======-->
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <ul>
                                                            <li class="mega-list-title">
                                                                <a href="product?category=Chairs"><i
                                                                        class="fas fa-couch u-s-m-r-6"></i>Seating</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Armchairs">Armchairs</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Sofas">Sofas</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Benches">Benches</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Stools">Stools</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <ul>
                                                            <li class="mega-list-title">

                                                                <a href="product?category=Tables"><i
                                                                        class="fas fa-desktop u-s-m-r-6"></i>Tables</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Dining+Tables">Dining
                                                                    Tables</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Coffee+Tables">Coffee
                                                                    Tables</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Side+Tables">Side
                                                                    Tables</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Bedside+Tables">Bedside
                                                                    Tables</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <ul>
                                                            <li class="mega-list-title">
                                                                <a href="product?category=Storage"><i
                                                                        class="fas fa-box  u-s-m-r-6"></i>Storage</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Wardrobes">Wardrobes</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Cabinets">Cabinets</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Dressers">Dressers</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Bookcases">Bookcases</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Shelving+Units">Shelving
                                                                    Units</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <ul>
                                                            <li class="mega-list-title">
                                                                <a href="product?category=Beds"><i
                                                                        class="fas fa-bed  u-s-m-r-6"></i>Beds</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Bed+Frames">Bed Frames</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Mattresses">Mattresses</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Nightstands">Nightstands</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Bunk+Beds">Bunk Beds</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <ul>
                                                            <li class="mega-list-title">
                                                                <a href="product?category=Desks"><i
                                                                        class="fas fa-briefcase  u-s-m-r-6"></i>Desks &
                                                                    Workstations</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Writing+Desks">Writing
                                                                    Desks</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Computer+Desks">Computer
                                                                    Desks</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Office+Chairs">Office
                                                                    Chairs</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Filing+Cabinets">Filing
                                                                    Cabinets</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <ul>
                                                            <li class="mega-list-title">
                                                                <a href="product?category=Dining"><i
                                                                        class="fas fa-utensils  u-s-m-r-6"></i>Dining
                                                                    Room</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Dining+Sets">Dining
                                                                    Sets</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Buffets">Buffets</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <ul>
                                                            <li class="mega-list-title">
                                                                <a href="product?category=Children"><i
                                                                        class="fas fa-child  u-s-m-r-6"></i>Children's
                                                                    Furniture</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Cribs">Cribs</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Kid+Beds">Kid Beds</a>
                                                            </li>
                                                            <li>
                                                                <a href="product?subcategory=Study+Desks">Study
                                                                    Desks</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>

                                                <!--====== End - Mega Menu Row ======-->
                                                <br>

                                            </div>
                                            <!--====== End - Furniture & Decor ======-->


                                            <!--====== No Sub Categories ======-->
                                            <div class="mega-menu-content">
                                                <h5>No Categories</h5>
                                            </div>
                                            <!--====== End - No Sub Categories ======-->

                                        </div>
                                    </div>
                                    <!--====== End - Mega Menu ======-->
                                </li>
                            </ul>
                            <!--====== End - List ======-->
                        </div>
                        <!--====== End - Menu ======-->
                    </div>
                    <!--====== End - Dropdown Main plugin ======-->


                    <!--====== Dropdown Main plugin ======-->
                    <div class="menu-init" id="navigation2">

                        <button class="btn btn--icon toggle-button fas fa-cog" type="button"></button>

                        <!--====== Menu ======-->
                        <div class="ah-lg-mode">

                            <span class="ah-close">✕ Close</span>

                            <!--====== List ======-->
                            <ul class="ah-list ah-list--design2 ah-list--link-color-secondary">
                                <li>

                                    <a href="home"><i class="fas fa-home u-s-m-r-6"></i>HOME</a></li>
                                <li>

                                    <a href="product"><i class="fas fa-box u-s-m-r-6"></i>PRODUCTS</a></li>
                                <li>

                                    <a href="about"><i class="fas fa-address-card u-s-m-r-6"></i>ABOUT US</a></li>
                            </ul>
                            <!--====== End - List ======-->
                        </div>
                        <!--====== End - Menu ======-->
                    </div>
                    <!--====== End - Dropdown Main plugin ======-->


                    <!--====== Dropdown Main plugin ======-->
                    <div class="menu-init" id="navigation3">

                        <button class="btn btn--icon toggle-button fas fa-shopping-bag toggle-button-shop"
                                type="button"></button>

                        <span class="total-item-round">2</span>

                        <!--====== Menu ======-->
                        <div class="ah-lg-mode">

                            <span class="ah-close">✕ Close</span>

                            <!--====== List ======-->
                            <ul class="ah-list ah-list--design1 ah-list--link-color-secondary">
                                <li>

                                    <a href="home"><i class="fas fa-home"></i></a></li>
                                <li class="has-dropdown">

                                    <a class="mini-cart-shop-link"><i class="fas fa-shopping-bag"></i>

                                        <span class="total-item-round">2</span></a>

                                    <!--====== Dropdown ======-->

                                    <span class="js-menu-toggle"></span>
                                    <div class="mini-cart">

                                        <!--====== Mini Product Container ======-->
                                        <div class="mini-product-container gl-scroll u-s-m-b-15">

                                            <!--====== Card for mini cart ======-->
                                            <div class="card-mini-product">
                                                <div class="mini-product">
                                                    <div class="mini-product__image-wrapper">

                                                        <a class="mini-product__link" href="product-detail.html">

                                                            <img class="u-img-fluid"
                                                                 src="images/product/electronic/product3.jpg"
                                                                 alt=""></a></div>
                                                    <div class="mini-product__info-wrapper">

                                                            <span class="mini-product__category">

                                                                <a href="shop-side-version-2.html">Electronics</a></span>

                                                        <span class="mini-product__name">

                                                                <a href="product-detail.html">Yellow Wireless Headphone</a></span>

                                                        <span class="mini-product__quantity">1 x</span>

                                                        <span class="mini-product__price">$8</span></div>
                                                </div>

                                                <a class="mini-product__delete-link far fa-trash-alt"></a>
                                            </div>
                                            <!--====== End - Card for mini cart ======-->


                                            <!--====== Card for mini cart ======-->
                                            <div class="card-mini-product">
                                                <div class="mini-product">
                                                    <div class="mini-product__image-wrapper">

                                                        <a class="mini-product__link" href="product-detail.html">

                                                            <img class="u-img-fluid"
                                                                 src="images/product/electronic/product18.jpg"
                                                                 alt=""></a></div>
                                                    <div class="mini-product__info-wrapper">

                                                            <span class="mini-product__category">

                                                                <a href="shop-side-version-2.html">Electronics</a></span>

                                                        <span class="mini-product__name">

                                                                <a href="product-detail.html">Nikon DSLR Camera 4k</a></span>

                                                        <span class="mini-product__quantity">1 x</span>

                                                        <span class="mini-product__price">$8</span></div>
                                                </div>

                                                <a class="mini-product__delete-link far fa-trash-alt"></a>
                                            </div>
                                            <!--====== End - Card for mini cart ======-->


                                            <!--====== Card for mini cart ======-->
                                            <div class="card-mini-product">
                                                <div class="mini-product">
                                                    <div class="mini-product__image-wrapper">

                                                        <a class="mini-product__link" href="product-detail.html">

                                                            <img class="u-img-fluid"
                                                                 src="images/product/women/product8.jpg" alt=""></a>
                                                    </div>
                                                    <div class="mini-product__info-wrapper">

                                                            <span class="mini-product__category">

                                                                <a href="shop-side-version-2.html">Women Clothing</a></span>

                                                        <span class="mini-product__name">

                                                                <a href="product-detail.html">New Dress D Nice Elegant</a></span>

                                                        <span class="mini-product__quantity">1 x</span>

                                                        <span class="mini-product__price">$8</span></div>
                                                </div>

                                                <a class="mini-product__delete-link far fa-trash-alt"></a>
                                            </div>
                                            <!--====== End - Card for mini cart ======-->


                                            <!--====== Card for mini cart ======-->
                                            <div class="card-mini-product">
                                                <div class="mini-product">
                                                    <div class="mini-product__image-wrapper">

                                                        <a class="mini-product__link" href="product-detail.html">

                                                            <img class="u-img-fluid"
                                                                 src="images/product/men/product8.jpg" alt=""></a></div>
                                                    <div class="mini-product__info-wrapper">

                                                            <span class="mini-product__category">

                                                                <a href="shop-side-version-2.html">Men Clothing</a></span>

                                                        <span class="mini-product__name">

                                                                <a href="product-detail.html">New Fashion D Nice Elegant</a></span>

                                                        <span class="mini-product__quantity">1 x</span>

                                                        <span class="mini-product__price">$8</span></div>
                                                </div>

                                                <a class="mini-product__delete-link far fa-trash-alt"></a>
                                            </div>
                                            <!--====== End - Card for mini cart ======-->
                                        </div>
                                        <!--====== End - Mini Product Container ======-->


                                        <!--====== Mini Product Statistics ======-->
                                        <div class="mini-product-stat">
                                            <div class="mini-total">

                                                <span class="subtotal-text">SUBTOTAL</span>

                                                <span class="subtotal-value">$16</span></div>
                                            <div class="mini-action">

                                                <a class="mini-link btn--e-brand-b-2" href="checkout">PROCEED TO
                                                    CHECKOUT</a>

                                                <a class="mini-link btn--e-transparent-secondary-b-2" href="cart">VIEW
                                                    CART</a></div>
                                        </div>
                                        <!--====== End - Mini Product Statistics ======-->
                                    </div>
                                    <!--====== End - Dropdown ======-->
                                </li>
                            </ul>
                            <!--====== End - List ======-->
                            <!--====== End - List ======-->
                        </div>
                        <!--====== End - Menu ======-->
                    </div>
                    <!--====== End - Dropdown Main plugin ======-->
                </div>
                <!--====== End - Secondary Nav ======-->
            </div>
        </nav>
        <!--====== End - Nav 2 ======-->
    </header>
    <!--====== End - Main Header ======-->



    <!--====== Custom js ======-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.main-form').submit(function (e) {
                e.preventDefault();
                const search = $('#main-search').val();
                window.location.href = 'product?search=' + search;
            });
        });
    </script>
    <script src="js/header.js"></script>

