package controllers.servlets.user;

import com.google.gson.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.CartItemDto;
import models.DTOs.ProductDto;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.CartService;
import services.JWTService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                    //if quantity is not provided, default to 1
                    int quantity = request.getParameter("quantity") != null ? Integer.parseInt(request.getParameter("quantity")) : 1;
                    result = cartService.addProductToCart(userId, productId, quantity);
                    System.out.println(userId + " " + productId + " " + quantity);
                    break;
                case "removeProduct":
                    System.out.println("removeProduct-----");
                    Long removeProductId = Long.parseLong(request.getParameter("productId"));
                    System.out.println(removeProductId + "----------");
                    result = cartService.removeProductFromCart(userId, removeProductId);
                    System.out.println(result);
                    break;
                case "decrementProductQuantity":
                    System.out.println("decrementProductQuantity-----");
                    Long decrementProductId = Long.parseLong(request.getParameter("productId"));
                    System.out.println(decrementProductId + "----------");
                    result = cartService.decrementProductQuantity(userId, decrementProductId);
                    System.out.println(result);
                    break;
                case "clearCart":
                    result = cartService.clearCart(userId);
                    break;
                case "addProductsToCart":
                    System.out.println("addProductsToCart-----");
                    result = handleAddProductsToCartRequest(userId, request);
                    break;
                case "retrieveCart":
                    System.out.println("retrieveCart-----");
                    List<CartItemDto> cart = cartService.retrieveCart(userId);
                    System.out.println(cart);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(new Gson().toJson(cart));
                    return;
                default:
                    result = false;
                    break;
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            System.out.println(result);
            response.getWriter().write(String.valueOf(result));
        } catch (InvalidJwtException | MalformedClaimException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean handleAddProductsToCartRequest(Long userId, HttpServletRequest request) {
        Gson gson = new Gson();
        try {
            String shoppingDataJson = request.getParameter("shoppingData");
            if (shoppingDataJson == null || shoppingDataJson.isEmpty()) {
                System.out.println("Shopping data is empty or null.");
                return false;
            }

            // Deserialize the JSON string into a JSON object
            JsonObject jsonObject = gson.fromJson(shoppingDataJson, JsonObject.class);

            // Extract the "products" array from the JSON object
            JsonArray productsArray = jsonObject.getAsJsonArray("products");

            // Extract the "productCounts" object from the JSON object
            JsonObject productCountsObject = jsonObject.getAsJsonObject("productCounts");

            // Create a list to hold the ProductDto objects
            List<ProductDto> products = new ArrayList<>();

            // Iterate over the products array and deserialize each element into a ProductDto object
            for (JsonElement element : productsArray) {
                JsonObject productJson = element.getAsJsonObject();

                // Deserialize the product fields
                ProductDto productDto = gson.fromJson(productJson, ProductDto.class);

                // Extract the product ID or name to retrieve its count from productCountsObject
                String name = productDto.getName();

                // Retrieve the quantity for the current product from productCountsObject
                int quantity = productCountsObject.get(name).getAsInt();

                // Set the quantity in the ProductDto object
                productDto.setQuantity(quantity);

                // Add the ProductDto object to the list
                products.add(productDto);
            }

            // Process the list of ProductDto objects as needed
            cartService.addProductsToCart(userId, products);

            return true; // Return true if successful
        } catch (JsonSyntaxException e) {
            System.out.println("Error while parsing shopping data JSON: " + e.getMessage());
            return false;
        }
    }


}
