<%@page session="false" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <title>User Info</title>
    <link href="assets/images/logos/favicon.png" rel="shortcut icon" type="image/png"/>
    <link href="assets/css/styles.min.css" rel="stylesheet"/>
</head>

<body>
<!--  Body Wrapper -->
<div class="page-wrapper" data-header-position="fixed" data-layout="vertical" data-navbarbg="skin6"
     data-sidebar-position="fixed"
     data-sidebartype="full" id="main-wrapper">
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div>
            <div class="brand-logo d-flex align-items-center justify-content-between">
                <a class="text-nowrap logo-img" href="/molla/view/admin/home">
                    <img alt="" src="assets/images/logos/mollalogotransparent.png" width="180"/>
                </a>
                <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
                    <i class="ti ti-x fs-8"></i>
                </div>
            </div>
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
                <ul id="sidebarnav">
                    <li class="nav-small-cap">
                        <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                        <span class="hide-menu">Home</span>
                    </li>
                    <li class="sidebar-item">
                        <a aria-expanded="false" class="sidebar-link" href="/molla/view/admin/home">
                <span>
                  <i class="ti ti-layout-dashboard"></i>
                </span>
                            <span class="hide-menu">Dashboard</span>
                        </a>
                    </li>
                    <li class="nav-small-cap">
                        <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                        <span class="hide-menu">Users</span>
                    </li>
                    <li class="sidebar-item">
                        <a aria-expanded="false" class="sidebar-link" href="/molla/view/admin/allusers">
                <span>
                  <i class="ti ti-user"></i>
                </span>
                            <span class="hide-menu">View All Users</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a aria-expanded="false" class="sidebar-link" href="/molla/view/admin/adduser">
                <span>
                  <i class="ti ti-user-plus"></i>
                </span>
                            <span class="hide-menu">Add new</span>
                        </a>
                    </li>
                    <li class="nav-small-cap">
                        <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                        <span class="hide-menu">Products</span>
                    </li>
                    <li class="sidebar-item">
                        <a aria-expanded="false" class="sidebar-link" href="/molla/view/admin/allproducts">
                <span>
                  <i class="ti ti-package"></i>
                </span>
                            <span class="hide-menu">View All</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a aria-expanded="false" class="sidebar-link" href="/molla/view/admin/addproductpage">
                <span>
                  <i class="ti ti-plus"></i>
                </span>
                            <span class="hide-menu">Add Product</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
    </aside>
    <!--  Sidebar End -->
    <!--  Main wrapper -->
    <div class="body-wrapper">
        <!--  Header Start -->
        <header class="app-header">
            <nav class="navbar navbar-expand-lg navbar-light">
                <ul class="navbar-nav">
                    <li class="nav-item d-block d-xl-none">
                        <a class="nav-link sidebartoggler nav-icon-hover" href="javascript:void(0)" id="headerCollapse">
                            <i class="ti ti-menu-2"></i>
                        </a>
                    </li>

                </ul>
                <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
                    <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">
                        <span>Welcome Hamada </span>
                        <li class="nav-item dropdown">
                            <a aria-expanded="false" class="nav-link nav-icon-hover" data-bs-toggle="dropdown"
                               href="javascript:void(0)"
                               id="drop2">
                                <img alt="" class="rounded-circle" height="35" src="assets/images/profile/user-1.jpg"
                                     width="35">
                            </a>
                            <div aria-labelledby="drop2"
                                 class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up">
                                <div class="message-body">
                                    <a class="d-flex align-items-center gap-2 dropdown-item" href="javascript:void(0)">
                                        <i class="ti ti-user fs-6"></i>
                                        <p class="mb-0 fs-3">My Profile</p>
                                    </a>
                                    <a class="d-flex align-items-center gap-2 dropdown-item" href="javascript:void(0)">
                                        <i class="ti ti-mail fs-6"></i>
                                        <p class="mb-0 fs-3">My Account</p>
                                    </a>
                                    <a class="d-flex align-items-center gap-2 dropdown-item" href="javascript:void(0)">
                                        <i class="ti ti-list-check fs-6"></i>
                                        <p class="mb-0 fs-3">My Task</p>
                                    </a>
                                    <a class="btn btn-outline-primary mx-3 mt-2 d-block"
                                       href="./authentication-login.html">Logout</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <!--  Header End -->
        <div class="container-fluid">
            <div class="container-fluid">
                <div class="card">
                    <div class="card-body">

                        <form class="row">

                            <fieldset disabled class="col-md-3">
                                <h2 class=" fw-semibold mb-4">User Details</h2>
                                <div class="card">
                                    <img src="assets/images/profile/user-1.jpg" class="rounded-circle" alt="...">

                                </div>
                            </fieldset>

                            <fieldset disabled class="col-md-6">
                                <h5 class="card-title mt-5">User ID</h5>
                                <p class="card-text mt-1">${user.id}</p>

                                <label for="nameInput" class="form-label card-title">UserName</label>
                                <input type="text " class="form-control" id="nameInput" aria-describedby="nameHelp"
                                       value="${user.name}">

                                <label for="creditInput" class="form-label card-title">Credit Limit</label>
                                <input type="text " class="form-control" id="creditInput" aria-describedby="creditHelp"
                                       value="${user.creditLimit}">


                                <h5 class="card-title mt-3">Birthday</h5>
                                <input class="form-control" type="date" id="date" name="date" value="${user.birthday}">

                                <label for="jobInput" class="form-label card-title">Job</label>
                                <input type="text " class="form-control" id="jobInput" aria-describedby="jobHelp"
                                       value="${user.job}">

                            </fieldset>
                            <fieldset disabled class="col-md-12">
                                <div class="card mt-5">
                                    <div class="card-body">
                                        <h5 class="card-title mt-3">Interests</h5>
                                        <span class="badge bg-info rounded-3 fw-semibold m-1">${user.interest}</span>

                                        <h5 class="card-title fw-semibold mt-4">Addresses</h5>
                                        <table class="table text-nowrap mb-0 align-middle">
                                            <thead class="text-dark fs-4">
                                            <tr>
                                                <th class="border-bottom-0">
                                                    <h6 class="fw-semibold mb-0">#</h6>
                                                </th>
                                                <th class="border-bottom-0">
                                                    <h6 class="fw-semibold mb-0">Street</h6>
                                                </th>
                                                <th class="border-bottom-0">
                                                    <h6 class="fw-semibold mb-0">City</h6>
                                                </th>
                                                <th class="border-bottom-0">
                                                    <h6 class="fw-semibold mb-0">State</h6>
                                                </th>
                                                <th class="border-bottom-0">
                                                    <h6 class="fw-semibold mb-0">Country</h6>
                                                </th>
                                                <th class="border-bottom-0">
                                                    <h6 class="fw-semibold mb-0">Zipcode</h6>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody id="addressTableBody">
    <c:forEach items="${user.addresses}" var="address">
                                            <tr>
                                                <td class="border-bottom-0">
                                                    <h6 class="fw-semibold mb-1">${address.id}</h6>
                                                </td>
                                                <td class="border-bottom-0">
                                                    <input type="text" class="form-control form-control-sm"
                                                           value="${address.street}" name="street[]">
                                                </td>
                                                <td class="border-bottom-0">
                                                    <input type="text" class="form-control form-control-sm"
                                                           value="${address.city}" name="city[]">
                                                </td>
                                                <td class="border-bottom-0">
                                                    <input type="text" class="form-control form-control-sm"
                                                           value="${address.state}" name="state[]">
                                                </td>
                                                <td class="border-bottom-0">
                                                    <input type="text" class="form-control form-control-sm"
                                                           value="${address.country}" name="country[]">
                                                </td>
                                                <td class="border-bottom-0">
                                                    <input type="text" class="form-control form-control-sm" value="${address.zipCode}"
                                                           name="zipcode[]">
                                                </td>
                                            </tr>
    </c:forEach>
                                            </tbody>

                                        </table>

                                        </button>
                                    </div>
                                </div>

                            </fieldset>

                        </form>
                        <hr>
                        <h2 class=" fw-semibold mb-4">Order History</h2>
                        <hr>
                        <div class="card-body p-4">
                            <ul class="timeline-widget mb-0 position-relative mb-n5">

    <c:forEach items="${orders}" var="order">
                                <li class="timeline-item d-flex position-relative overflow-hidden">
                                    <div class="timeline-desc fs-3 text-dark mt-n1 fw-semibold">
                                        <span class="mb-0 fw-normal">${order.orderDate}</span>
                                    </div>
                                    <div class="timeline-badge-wrap d-flex flex-column align-items-center">
                                        <span class="timeline-badge border-2 border border-info flex-shrink-0 my-8"></span>
                                        <span class="timeline-badge-border d-block flex-shrink-0"></span>
                                    </div>

                                    <div class="timeline-desc fs-3 text-dark mt-n1 fw-semibold">New Checkout recorded #${order.id}

                                        <div class="container-fluid">
                                            <div class="col-lg-8 d-flex align-items-stretch">
                                                <div>
                                                    <div class="table-responsive">
                                                        <table class="table text-nowrap  align-middle">
                                                            <thead class="text-dark fs-4">
                                                            <tr>
                                                                <th >
                                                                    <h6 class="fw-semibold mb-0">Id</h6>
                                                                </th>
                                                                <th >
                                                                    <h6 class="fw-semibold mb-0">Item</h6>
                                                                </th>
                                                                <th >
                                                                    <h6 class="fw-semibold mb-0">Quantity</h6>
                                                                </th>
                                                                <th >
                                                                    <h6 class="fw-semibold mb-0">Unit Price</h6>
                                                                </th>

                                                            </tr>
                                                            </thead>
                                                            <tbody>
               <c:forEach items="${order.orderItems}" var="orderItem">
                                                            <tr>
                                                                <td >
                                                                    <p class="mb-0 fw-normal">${orderItem.product.productId}</p>
                                                                </td>
                                                                <td >
                                                                    <p class="mb-0 fw-normal">${orderItem.product.name}</p>
                                                                </td>
                                                                <td >
                                                                    <p class="mb-0 fw-normal">${orderItem.quantity}</p>
                                                                </td>
                                                                <td >
                                                                    <p class="mb-0 fw-normal">$${orderItem.product.price}</p>
                                                                </td>
                                                            </tr>
               </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <h5 class="text-primary d-block fw-normal">Total: $${order.totalAmount}</h5>
                                    </div>
                                </li>
    </c:forEach>
                            </ul>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/viewuser.js"></script>
<script src="assets/libs/jquery/dist/jquery.min.js"></script>
<script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/sidebarmenu.js"></script>
<script src="assets/js/app.min.js"></script>
<script src="assets/libs/simplebar/dist/simplebar.js"></script>
</body>

</html>