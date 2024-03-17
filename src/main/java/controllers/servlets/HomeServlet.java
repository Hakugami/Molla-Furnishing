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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HomeServlet: processing request");
        List<ProductDto> products = getProductsDTO(req);
        req.setAttribute("products", products);
        System.out.println(products);
        req.getRequestDispatcher(UrlMapping.HOME.getPageName()).forward(req, resp);
    }

    private List<ProductDto> getProductsDTO(HttpServletRequest req) {

        // Create a ProductFilter object
        ProductFilter filter = new ProductFilter();

        // Set Filters
        filter.setSortBy("dateAdded");
        filter.setSortOrder("desc");
        Cookie cookie = CookiesUtil.getCookie(req.getCookies(), "Authorization");
        if (cookie != null) {
            JwtClaims claims = null;
            try {
                claims = JWTService.getInstance().validateToken(cookie.getValue(), req.getRemoteAddr());
            } catch (InvalidJwtException | UnknownHostException e) {
            }
            String interests = null;
            try {
                interests = claims.getClaimValue("interest", String.class);
            } catch (MalformedClaimException e) {
            }
            filter.setCategory(interests);
        }

        int pageInt = 1;
        int limitInt = 8;
        // Call the getProductByFilter method from the ProductService class
        ProductService productService = ProductService.getInstance();

        return productService.getProductByFilter(pageInt, limitInt, filter);
    }
}
