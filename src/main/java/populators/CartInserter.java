package populators;

import services.CartService;

public class CartInserter {
    public CartService cartService;
    public void insertCart() {
        cartService = new CartService();
        cartService.addProductToCart(1L, 10L, 2);
    }

    public void removeProductFromCart() {
        cartService = new CartService();
        cartService.removeProductFromCart(1L, 1L);
    }

    public void decrementProductQuantity() {
        cartService = new CartService();
        cartService.decrementProductQuantity(1L, 10L);
    }

    public static void main(String[] args) {
        CartInserter cartInserter = new CartInserter();
        cartInserter.insertCart();
//        cartInserter.removeProductFromCart();
//        cartInserter.decrementProductQuantity();
    }

}
