package models.DTOs;

import lombok.Value;
import models.entity.CartItem;

import java.io.Serializable;

/**
 * DTO for {@link CartItem}
 */
@Value
public class CartItemDto implements Serializable {
    Long id;
    ProductDto product;
    int quantity;
    ShoppingCartDto shoppingCart;
}