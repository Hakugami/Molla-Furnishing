package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.ProductDto;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import persistence.repositories.helpers.ProductFilter;
import services.JWTService;
import services.ProductService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public class HomeServlet extends HttpServlet {

    private JwtClaims claims;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HomeServlet: processing request");

        List<ProductDto> recentProducts = getRecentProductsDTO(req);
        req.setAttribute("recentProducts", recentProducts);

        if (isUserLoggedIn(req)) {
            List<ProductDto> personalizedProducts = getPersonalizedProductsDTO();
            req.setAttribute("personalizedProducts", personalizedProducts);
        }

        req.getRequestDispatcher(UrlMapping.HOME.getPageName()).forward(req, resp);
    }

    private List<ProductDto> getRecentProductsDTO(HttpServletRequest req) {

        // Create a ProductFilter object
        ProductFilter filter = new ProductFilter();

        // Set Filters
        filter.setSortBy("dateAdded");
        filter.setSortOrder("desc");

        int pageInt = 1;
        int limitInt = 8;
        // Call the getProductByFilter method from the ProductService class
        ProductService productService = ProductService.getInstance();

        return productService.getProductByFilter(pageInt, limitInt, filter);
    }

    public boolean isUserLoggedIn(HttpServletRequest request) {
        Cookie authorizationCookie = CookiesUtil.getCookie(request.getCookies(), "Authorization");
        if (authorizationCookie != null) {
            try {
                claims = JWTService.getInstance().validateToken(authorizationCookie.getValue(), request.getRemoteAddr());
                return true;
            } catch (InvalidJwtException | UnknownHostException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private List<ProductDto> getPersonalizedProductsDTO() {
        try {

            // Get user's interests from JWT claims
            var interests = claims.getClaimValue("interest", String.class);

            // Set up product filter based on user's interests
            ProductFilter filter = new ProductFilter();
            filter.setCategory(interests);

            int page = 1;
            int limit = 8;

            ProductService productService = ProductService.getInstance();
            return productService.getProductByFilter(page, limit, filter);

        } catch (MalformedClaimException e) {
            return null;
        }
    }


}
