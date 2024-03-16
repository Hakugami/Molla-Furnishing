package services;


import models.DTOs.ProductDto;
import models.entity.Product;
import models.entity.ShoppingCart;

public class ShoppingCartService {
    public void addToCart(ShoppingCart cart, ProductDto product) {
        cart.addItem(product);
    }

    public void removeFromCart(ShoppingCart cart, Product product) {
        cart.removeItem(product);
    }
}
