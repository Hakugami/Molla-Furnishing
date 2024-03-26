package populators;

import services.CartService;
import services.CheckoutService;

public class OrderInserter {
    private final CheckoutService checkoutService;
    private final CartService cartService;

    public OrderInserter() {
        checkoutService = new CheckoutService();
        cartService = new CartService();
    }

    public static void main(String[] args) {
        OrderInserter orderInserter = new OrderInserter();
        orderInserter.insertOrder();
    }

    public void insertOrder() {
        checkoutService.checkout(1L, 1L);
        cartService.clearCart(1L);
    }
}
