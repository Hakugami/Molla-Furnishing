package controllers.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.CartService;
import services.JWTService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends HttpServlet {
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        super.init();
        cartService = new CartService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(UrlMapping.CART.getPageName()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = CookiesUtil.getCookie(request.getCookies(), "Authorization");
        try {
            JwtClaims claims = JWTService.getInstance().validateToken(cookie.getValue(), request.getRemoteAddr());
            Long userId = Long.parseLong(claims.getSubject());
            boolean result = false;
            String action = request.getParameter("action");
            switch (action) {
                case "addProduct":
                    Long productId = Long.parseLong(request.getParameter("productId"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    result = cartService.addProductToCart(userId, productId, quantity);
                    break;
                case "removeProduct":
                    Long removeProductId = Long.parseLong(request.getParameter("productId"));
                    result = cartService.removeProductFromCart(userId, removeProductId);
                    break;
                case "decrementProductQuantity":
                    Long decrementProductId = Long.parseLong(request.getParameter("productId"));
                    int newQuantity = cartService.decrementProductQuantity(userId, decrementProductId);
                    response.getWriter().write(String.valueOf(newQuantity));
                    break;
                case "clearCart":
                    result = cartService.clearCart(userId);
                    break;
                case "addProductsToCart":
                    result = handleAddProductsToCartRequest(userId, request);
                    break;
                default:
                    result = false;
                    break;
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.valueOf(result));
        } catch (InvalidJwtException | MalformedClaimException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean handleAddProductsToCartRequest(Long userId, HttpServletRequest request) {
        Map<Long, Integer> products = new HashMap<>();
        String[] productIds = request.getParameterValues("productId");
        String[] quantities = request.getParameterValues("quantity");

        for (int i = 0; i < productIds.length; i++) {
            Long id = Long.parseLong(productIds[i]);
            int qty = Integer.parseInt(quantities[i]);
            products.put(id, qty);
        }

        return cartService.addProductsToCart(userId, products);
    }
}
