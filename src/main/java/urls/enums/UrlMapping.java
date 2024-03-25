package urls.enums;

import lombok.Getter;

@Getter
public enum UrlMapping {
    CONTEXT_DEFAULT("", "/view", "FrontController", ""),
    LOGIN("login", "/login", "LoginServlet", "/signin.jsp"),
    REGISTER("register", "/register", "RegisterServlet", "/signup.jsp"),
    RETRIEVE_PRODUCTS("RetrieveProducts", "/retrieveProducts", "RetrieveProducts", ""),
    RETRIEVE_PRODUCT_COUNT("RetrieveProductCount", "/retrieveProductCount", "RetrieveProductCountServlet", ""),
    RETRIEVE_PRODUCT_BY_ID("RetrieveProductById", "/retrieveProductById", "RetrieveProductByIdServlet", ""),
    HOME("home", "/home", "HomeServlet", "/index-3.jsp"),
    PROFILE("profile", "/profile", "ProfileServlet", "/dashboard.jsp"),
    PRODUCTS("product", "/product", "ProductsServlet", "/shop-grid-full.jsp"),
    PRODUCTPAGE("ProductPage", "/ProductPage", "ProductPageServlet", "/product-detail.jsp"),
    REVIEW("Review", "/Review", "ReviewServlet", "/product-detail.jsp"),
    EDITPROFILE("editProfile", "/editProfile", "EditProfileServlet", "/dash-edit-profile.jsp"),
    EMAILVALIDATION("emailValidation", "/emailValidation", "EmailValidationServlet", ""),
    PASSWORDVALIDATION("passwordValidation", "/passwordValidation", "PasswordValidationServlet", ""),
    PHONEVALIDATION("phoneValidation", "/phoneValidation", "PhoneNumberValidationServlet", ""),
    ABOUT("about", "/about", "AboutServlet", "/about.jsp"),
    WISHLIST("wishlist", "/wishlist", "WishlistServlet", "/wishlist.jsp"),
    CART("cart", "/cart", "CartServlet", "/cart.jsp"),
    LOGOUT("logout", "/logout", "LogoutServlet", ""),
    LOADPROFILE("loadProfile", "/loadProfile", "LoadProfileServlet", ""),
    MYPROFILE("myProfile", "/myProfile", "MyProfileServlet", "/dash-my-profile.jsp"),
    ADDRESSOPERATION("addressOperation", "/addressOperation", "AddressServlet", "/dash-address-add.jsp"),
    ADDRESS("address", "/address", "AddressBookServlet", "/dash-address-book.jsp"),
    RESETPASSWORD("sendResetPassword", "/sendResetPassword", "SendResetPasswordServlet", "/lost-password.jsp"),
    RESETPASSWORDCHANGE("resetPasswordChange", "/resetPasswordChange", "ResetPasswordChangeServlet", "/lost-password-change.jsp"),
    //Admin page
    ADMIN_HOME("adminhome", "/admin/home", "AdminHomeServlet", "/Adminpanel/index.html"),
    ADMIN_LOGIN("adminlogin", "/admin/login", "AdminLoginServlet", "/Adminpanel/adminlogin.html"),
    ADMIN_ALL_PRODUCTS("adminallproducts", "/admin/all/products", "AdminAllProductsServlet", "/Adminpanel/allproducts.html"),
    ADMIN_VIEW_PRODUCT("adminviewproduct", "/admin/viewproduct", "AdminViewProductPageServlet", "/Adminpanel/viewproduct.html"),
    ADMIN_ALL_USERS("adminallusers", "/admin/allusers", "AdminAllUsersServlet", "/Adminpanel/allusers.jsp"),
    ADMIN_VIEW_USER("adminviewuser", "/admin/viewuser", "AdminViewUserServlet", "/Adminpanel/viewuser.jsp"),
    ADMIN_ADD_USER("adminadduser", "/admin/adduser", "AdminAddUserServlet", "/Adminpanel/adduser.html"),
    ADMIN_ADD_PRODUCT_PAGE("adminaddproductpage", "/admin/addproductpage", "AdminAddProductPageServlet", "/Adminpanel/addproduct.html"),
    //Admin Operations
    ADMINUPDATEPRODUCT("adminupdateproduct", "/admin/updateproduct", "AdminUpdateProductServlet", ""),
    ADMIN_ADD_PRODUCT("adminaddproduct", "/admin/addproduct", "AdminAddProductServlet", ""),
    ;

    private final String command;
    private final String url;
    private final String servletName;
    private final String pageName;


    UrlMapping(String command, String url, String servletName, String pageName) {
        this.command = command;
        this.url = url;
        this.servletName = servletName;
        this.pageName = pageName;
    }

    public static String getServletNameForCommand(String commandName) {
        for (UrlMapping mapping : UrlMapping.values()) {
            if (mapping.getCommand().equals(commandName)) {
                return mapping.getServletName();
            }
        }
        throw new IllegalArgumentException("No mapping found for command: " + commandName);
    }

    public String getContextEmbeddedUrl(String contextPath) {
        return contextPath + CONTEXT_DEFAULT.getUrl() + url;
    }


}
