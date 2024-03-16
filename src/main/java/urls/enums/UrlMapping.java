package urls.enums;

import lombok.Getter;

@Getter
public enum UrlMapping {
    CONTEXT_DEFAULT("", "/view", "FrontController",""),
    LOGIN("login", "/login", "LoginServlet","/signin.jsp"),
    REGISTER("register", "/register", "RegisterServlet","/signup.jsp"),
    RETRIEVE_PRODUCTS("RetrieveProducts", "/retrieveProducts", "RetrieveProducts",""),
    HOME("home", "/home", "HomeServlet","/index-3.jsp"),
    PROFILE("profile","/profile","ProfileServlet","/dashboard.html"),
    PRODUCTS("product","/product","ProductsServlet","/shop-grid-full.jsp"),
    PRODUCTPAGE("ProductPage","/ProductPage","ProductPageServlet","/product-detail.html"),
    REVIEW("Review","/Review","ReviewServlet","/product-detail.html"),
    ABOUT("about", "/about", "AboutServlet", "/about.jsp"),
    WISHLIST("wishlist", "/wishlist", "WishlistServlet", "/wishlist.jsp"),
    CART("cart", "/cart", "CartServlet", "/cart.jsp");


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
