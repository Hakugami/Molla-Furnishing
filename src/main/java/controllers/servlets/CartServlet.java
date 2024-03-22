package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CartService;
import services.JWTService;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        Long userId = null;
        boolean result = false;

        if (JWTService.isUserLoggedIn(token)) {
            userId = JWTService.getUserIdFromToken(token);

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
                    result = cartService.decrementProductQuantity(userId, decrementProductId);
                    break;
                case "clearCart":
                    result = cartService.clearCart(userId);
                    break;
                case "addProductsToCart":
                    result = handleAddProductsToCartRequest(userId, request);
                    break;
                default:
                    // Unknown action
                    result = false;
                    break;
            }
        }

        response.getWriter().println(result ? "Success" : "Failed");
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
