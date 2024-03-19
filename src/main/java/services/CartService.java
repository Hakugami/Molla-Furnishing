package services;

import models.entity.CartItem;
import models.entity.Product;
import models.entity.User;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.impl.UserRepository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CartService {
    private final UserRepository userRepository;

    public CartService() {
        this.userRepository = new UserRepository();
    }

    public boolean addProductToCart(Long id, Long productId, int quantity) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            User user = userRepository.read(id, entityManager).orElse(null);
            if (user == null) {
                return false;
            }
            Optional<CartItem> cartItem = user.getCart().getCartItems().stream().filter(item -> Objects.equals(item.getProduct().getProductId(), productId)).findFirst();
            if (cartItem.isPresent()) {
                cartItem.get().setQuantity(cartItem.get().getQuantity() + quantity);
            } else {

                Product product = entityManager.find(Product.class, productId);
                user.getCart().addCartItem(product, quantity , product.getPrice()*quantity);
            }
            userRepository.update(user, entityManager);
            return true;
        });
    }

    public boolean removeProductFromCart(Long id, Long productId) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            User user = userRepository.read(id, entityManager).orElse(null);
            if (user == null) {
                return false;
            }
            Optional<CartItem> cartItem = user.getCart().getCartItems().stream().filter(item -> Objects.equals(item.getProduct().getProductId(), productId)).findFirst();
            if (cartItem.isPresent()) {
                user.getCart().removeProduct(cartItem.get().getProduct());
                userRepository.update(user, entityManager);
                return true;
            }
            return false;
        });
    }

    public boolean decrementProductQuantity(Long id, Long productId) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            User user = userRepository.read(id, entityManager).orElse(null);
            if (user == null) {
                return false;
            }
            Optional<CartItem> cartItem = user.getCart().getCartItems().stream()
                    .filter(item -> Objects.equals(item.getProduct().getProductId(), productId)).findFirst();
            if (cartItem.isPresent()) {
                CartItem item = cartItem.get();
                item.setQuantity(item.getQuantity() - 1);
                user.getCart().setTotalAmount(user.getCart().getTotalAmount() - item.getProduct().getPrice());
                if (item.getQuantity() == 0) {
                    user.getCart().getCartItems().remove(item);
                }
                userRepository.update(user, entityManager);
                return true;
            }
            return false;
        });
    }

    public boolean addProductsToCart(Long id, Map<Long, Integer> products) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            User user = userRepository.read(id, entityManager).orElse(null);
            if (user == null) {
                return false;
            }
            for (Map.Entry<Long, Integer> entry : products.entrySet()) {
                Long productId = entry.getKey();
                int quantity = entry.getValue();
                Optional<CartItem> cartItem = user.getCart().getCartItems().stream()
                        .filter(item -> Objects.equals(item.getProduct().getProductId(), productId)).findFirst();
                if (cartItem.isPresent()) {
                    cartItem.get().setQuantity(cartItem.get().getQuantity() + quantity);
                } else {
                    Product product = entityManager.find(Product.class, productId);
                    user.getCart().addCartItem(product, quantity, product.getPrice()*quantity);
                }
            }
            userRepository.update(user, entityManager);
            return true;
        });
    }

}
