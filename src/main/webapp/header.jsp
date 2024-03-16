<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html class="no-js" lang="en">
<head>
    <meta charset="UTF-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
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

                        <a class="main-logo" href="index.html">

                            <img src="images/logo/logo-1.png" alt=""></a>
                        <!--====== End - Main Logo ======-->


                        <!--====== Search Form ======-->
                        <form class="main-form">

                            <label for="main-search"></label>

                            <input class="input-text input-text--border-radius input-text--style-1" type="text" id="main-search" placeholder="Search">

                            <button class="btn btn--icon fas fa-search main-search-button" type="submit"></button></form>
                        <!--====== End - Search Form ======-->


                        <!--====== Dropdown Main plugin ======-->
                        <div class="menu-init" id="navigation">

                            <button class="btn btn--icon toggle-button fas fa-cogs" type="button"></button>

                            <!--====== Menu ======-->
                            <div class="ah-lg-mode">

                                <span class="ah-close">✕ Close</span>
                <c:choose>
                    <c:when test="${not empty user}">

                                <!--====== List (Logged-in User) ======-->
                                <ul class="ah-list ah-list--design1 ah-list--link-color-secondary">
                                    <li class="has-dropdown" data-tooltip="tooltip" data-placement="left" title="${user.name}">

                                        <a><i class="far fa-user-circle"></i></a>


                                        <!--====== Dropdown ======-->

                                        <span class="js-menu-toggle"></span>
                                        <ul style="width:120px">

                                            <li>

                                                <a href="profile"><i class="fas fa-user-circle u-s-m-r-6"></i>

                                                    <span>Account</span></a></li>

                                            <li>

                                                <a href="register"><i class="fas fa-lock-open u-s-m-r-6"></i>

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
                                    <li class="has-dropdown" data-tooltip="tooltip" data-placement="left" >

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

                                                            <a href="shop-side-version-2.html"><i class="fas fa-couch u-s-m-r-6"></i>

                                                                <span>Furniture & Decor</span></a>

                                                            <span class="js-menu-toggle js-toggle-mark"></span></li>
                                                        <li>

                                                            <a href="index.html"><i class="fas fa-couch u-s-m-r-6"></i>

                                                                <span>Furniture 2 & Decor 2</span></a>

                                                            <span class="js-menu-toggle"></span></li>
                                                    </ul>
                                                </div>

                                                <!--====== Furniture & Decor ======-->
                                                <div class="mega-menu-content js-active">

                                                    <!--====== Mega Menu Row ======-->
                                                    <div class="row">
                                                        <div class="col-lg-3">
                                                            <ul>
                                                                <li class="mega-list-title">

                                                                    <a href="shop-side-version-2.html">Category 1</a></li>
                                                                <li>

                                                                    <a href="shop-side-version-2.html">Sub Category 11</a></li>
                                                                <li>

                                                                    <a href="shop-side-version-2.html">Sub Category 12</a></li>
                                                            </ul>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <ul>
                                                                <li class="mega-list-title">

                                                                    <a href="shop-side-version-2.html">Category 2</a></li>
                                                                <li>

                                                                    <a href="shop-side-version-2.html">Sub Category 21</a></li>
                                                                <li>

                                                                    <a href="shop-side-version-2.html">Sub Category 22</a></li>
                                                            </ul>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <ul>
                                                                <li class="mega-list-title">

                                                                    <a href="shop-side-version-2.html">Category 3</a></li>
                                                                <li>

                                                                    <a href="shop-side-version-2.html">Sub Category 31</a></li>
                                                                <li>

                                                                    <a href="shop-side-version-2.html">Sub Category 32</a></li>
                                                            </ul>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <ul>
                                                                <li class="mega-list-title">

                                                                    <a href="shop-side-version-2.html">Category 4</a></li>
                                                                <li>

                                                                    <a href="shop-side-version-2.html">Sub Category 41</a></li>
                                                                <li>

                                                                    <a href="shop-side-version-2.html">SSub Category 42</a></li>
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

                                        <a href="home">HOME</a></li>
                                    <li>

                                        <a href="product">PRODUCTS</a></li>
                                    <li>

                                        <a href="about">ABOUT US</a></li>
                                </ul>
                                <!--====== End - List ======-->
                            </div>
                            <!--====== End - Menu ======-->
                        </div>
                        <!--====== End - Dropdown Main plugin ======-->


                        <!--====== Dropdown Main plugin ======-->
                        <div class="menu-init" id="navigation3">

                            <button class="btn btn--icon toggle-button fas fa-shopping-bag toggle-button-shop" type="button"></button>

                            <span class="total-item-round">2</span>

                            <!--====== Menu ======-->
                            <div class="ah-lg-mode">

                                <span class="ah-close">✕ Close</span>

                                <!--====== List ======-->
                                <ul class="ah-list ah-list--design1 ah-list--link-color-secondary">
                                    <li>

                                        <a href="home"><i class="fas fa-home"></i></a></li>
                                    <li>

                                        <a href="wishlist"><i class="far fa-heart"></i></a></li>
                                    <li class="has-dropdown">

                                        <a href="cart" class="mini-cart-shop-link">
                                            <i class="fas fa-shopping-bag"></i></a></li>
                                </ul>
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