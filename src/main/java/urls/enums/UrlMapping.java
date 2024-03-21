package urls.enums;

import lombok.Getter;

@Getter
public enum UrlMapping {
    CONTEXT_DEFAULT("", "/view", "FrontController", ""),
    LOGIN("login", "/login", "LoginServlet", "/signin.jsp"),
    REGISTER("register", "/register", "RegisterServlet", "/signup.jsp"),
    RETRIEVE_PRODUCTS("RetrieveProducts", "/retrieveProducts", "RetrieveProducts", ""),
    RETRIEVE_PRODUCT_COUNT("RetrieveProductCount", "/retrieveProductCount", "RetrieveProductCountServlet",""),
    HOME("home", "/home", "HomeServlet", "/index-3.jsp"),
    PROFILE("profile", "/profile", "ProfileServlet", "/dashboard.html"),
    PRODUCTS("product", "/product", "ProductsServlet", "/shop-grid-full.jsp"),
    PRODUCTPAGE("ProductPage", "/ProductPage", "ProductPageServlet", "/product-detail.html"),
    REVIEW("Review", "/Review", "ReviewServlet", "/product-detail.html"),
    EDITPROFILE("editProfile", "/editProfile", "EditProfileServlet", "/dash-edit-profile.html"),
    EMAILVALIDATION("emailValidation", "/emailValidation", "EmailValidationServlet", ""),
    PASSWORDVALIDATION("passwordValidation", "/passwordValidation", "PasswordValidationServlet", ""),
    PHONEVALIDATION("phoneValidation", "/phoneValidation", "PhoneNumberValidationServlet", ""),
    ABOUT("about", "/about", "AboutServlet", "/about.jsp"),
    WISHLIST("wishlist", "/wishlist", "WishlistServlet", "/wishlist.jsp"),
    CART("cart", "/cart", "CartServlet", "/cart.jsp"),
    LOGOUT("logout", "/logout", "LogoutServlet", ""),
    LOADPROFILE("loadProfile", "/loadProfile", "LoadProfileServlet", ""),
    MYPROFILE("myProfile", "/myProfile", "MyProfileServlet", "/dash-my-profile.html"),
    ADDRESSOPERATION("addressOperation", "/addressOperation", "AddressServlet", ""),
    RESETPASSWORD("sendResetPassword", "/sendResetPassword", "SendResetPasswordServlet", "/lost-password.html"),
    RESETPASSWORDCHANGE("resetPasswordChange", "/resetPasswordChange", "ResetPasswordChangeServlet", "/lost-password-change.html"),
    //Admin page
    ADMINHOME("adminhome","/admin/home","AdminHomeServlet","/Adminpanel/index.html"),
    ADMINLOGIN("adminlogin","/admin/login","AdminLoginServlet","/Adminpanel/adminlogin.html"),
    ADMINALLPRODUCTS("adminallproducts","/admin/all/products","AdminAllProductsServlet","/Adminpanel/allproducts.html"),
    ADMINVIEWPRODUCT("adminviewproduct","/admin/viewproduct","AdminViewProductServlet","/Adminpanel/viewproduct.html"),
    ADMINALLUSERS("adminallusers","/admin/allusers","AdminAllUsersServlet","/Adminpanel/allusers.html"),
    ADMINVIEWUSER("adminviewuser","/admin/viewuser","AdminViewUserServlet","/Adminpanel/viewuser.html"),
    ADMINADDUSER("adminadduser","/adminadduser","AdminAddUserServlet","/Adminpanel/adduser.html"),
    ADMINADDPRODUCT("adminaddproduct","/admin/addproduct","AdminAddProductServlet","/Adminpanel/addproduct.html");

    private final String command;
    private final String url;
    private final String servletName;
    private final String pageName;


    UrlMapping(String command, String url ,String servletName, String pageName) {
        this.command = command;
        this.url = url;
        this.servletName = servletName;
        this.pageName = pageName;
    }
    
    public String getContextEmbeddedUrl(String contextPath) {
        return contextPath +CONTEXT_DEFAULT.getUrl() + url;
    }


}
