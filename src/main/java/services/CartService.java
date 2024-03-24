package services;

import mappers.CartMapper;
import models.DTOs.CartItemDto;
import models.DTOs.ProductDto;
import models.entity.CartItem;
import models.entity.Product;
import models.entity.User;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.impl.UserRepository;

import java.util.ArrayList;
import java.util.List;
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
                System.out.println("user null");
                return false;
            }
            Product product = entityManager.find(Product.class, productId);
            if (product == null) {
                System.out.println("product null");
                return false; // Invalid product ID
            }
            Optional<CartItem> cartItem = user.getCart().getCartItems().stream().filter(item -> Objects.equals(item.getProduct().getProductId(), productId)).findFirst();
            if (cartItem.isPresent()) {
                if (product.getQuantity() < cartItem.get().getQuantity() + quantity) {
                    System.out.println("product2 null");
                    return false; // Not enough quantity in stock
                }
                cartItem.get().setQuantity(cartItem.get().getQuantity() + quantity);
            } else {
                if (product.getQuantity() < quantity) {
                    System.out.println("product3 null");
                    return false; // Not enough quantity in stock
                }
                user.getCart().addCartItem(product, quantity, product.getPrice() * quantity);
            }
            userRepository.update(user, entityManager);
            return true;
        });
    }

    public boolean removeProductFromCart(Long id, Long productId) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            System.out.println("removeProductFromCart");
            User user = userRepository.read(id, entityManager).orElse(null);
            if (user == null) {
                System.out.println("user null");
                return false;
            }
            Optional<CartItem> cartItem = user.getCart().getCartItems().stream().filter(item -> Objects.equals(item.getProduct().getProductId(), productId)).findFirst();
            if (cartItem.isPresent()) {
                System.out.println("cartItem present");
                user.getCart().removeProduct(cartItem.get().getProduct());
                System.out.println(user.getCart().getTotalAmount());
                userRepository.update(user, entityManager);
                return true;
            }
            return false;
        });
    }

    public boolean decrementProductQuantity(Long id, Long productId) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            System.out.println("decrementProductQuantity");
            System.out.println(id + " " + productId);
            User user = userRepository.read(id, entityManager).orElse(null);
            if (user == null) {
                System.out.println("user null");
                return false;
            }
            Optional<CartItem> cartItem = user.getCart().getCartItems().stream()
                    .filter(item -> Objects.equals(item.getProduct().getProductId(), productId)).findFirst();
            if (cartItem.isPresent()) {
                System.out.println("cartItem present");
                CartItem item = cartItem.get();
                item.setQuantity(item.getQuantity() - 1);
                user.getCart().setTotalAmount(user.getCart().getTotalAmount() - item.getProduct().getPrice());
                if (item.getQuantity() == 0) {
                    System.out.println("quantity 0");
                    user.getCart().getCartItems().remove(item);
                }
                System.out.println(user.getCart().getTotalAmount());
                userRepository.update(user, entityManager);
                return true;
            }
            return false;
        });
    }

    public boolean clearCart(Long id) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            User user = userRepository.read(id, entityManager).orElse(null);
            if (user == null) {
                return false;
            }
            user.getCart().removeAllProducts();
            userRepository.update(user, entityManager);
            return true;
        });
    }

    public boolean addProductsToCart(Long id, List<ProductDto> products) {
    return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
        System.out.println("addProductsToCart");
        User user = userRepository.read(id, entityManager).orElse(null);
        if (user == null) {
            return false;
        }
        for (ProductDto productDto : products) {
            Product product = entityManager.find(Product.class, productDto.getProductId());
            if (product == null) {
                return false; // Invalid product ID
            }
            Optional<CartItem> cartItem = user.getCart().getCartItems().stream()
                    .filter(item -> Objects.equals(item.getProduct().getProductId(), product.getProductId())).findFirst();
            if (cartItem.isPresent()) {
                System.out.println("cartItem present");
                cartItem.get().setQuantity(cartItem.get().getQuantity() + productDto.getQuantity());
            } else {
                System.out.println("cartItem not present");
                user.getCart().addCartItem(product, productDto.getQuantity(), product.getPrice() * productDto.getQuantity());
            }
        }
        userRepository.update(user, entityManager);
        return true;
    });
}

    public List<CartItemDto> retrieveCart(Long userId) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            User user = userRepository.read(userId, entityManager).orElse(null);
            if (user == null) {
                return null;
            }
            System.out.println("retrieveCart");
            List<CartItemDto> cartItemDtos = new ArrayList<>();
            List<CartItem> cartItems = user.getCart().getCartItems();
            for (CartItem cartItem : cartItems) {
                System.out.println(cartItem+" ____________Cart Item");
                CartItemDto cartItemDto = CartMapper.INSTANCE.cartItemToCartItemDto(cartItem);
                cartItemDtos.add(cartItemDto);
            }
            cartItemDtos.forEach(System.out::println);
            return cartItemDtos;
        });
    }
}
