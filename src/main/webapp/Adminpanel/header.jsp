<!--  Header Start -->
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Admin Dashboard</title>
  <link rel="shortcut icon" type="image/png" href="assets/images/logos/favicon.png" />
  <link rel="stylesheet" href="assets/css/styles.min.css" />
</head>

<body>
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
                    <a aria-expanded="false" class="nav-link nav-icon-hover" data-bs-toggle="dropdown" href="javascript:void(0)"
                       id="drop2">
                        <img alt="" class="rounded-circle" height="35" src="assets/images/profile/user-1.jpg" width="35">
                    </a>
                    <div aria-labelledby="drop2" class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up">
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
                            <a class="btn btn-outline-primary mx-3 mt-2 d-block" href="./authentication-login.html">Logout</a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</header>
</body>
</head>
</html>
<!--  Header End -->