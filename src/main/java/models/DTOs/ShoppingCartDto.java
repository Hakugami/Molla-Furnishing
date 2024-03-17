package models.DTOs;

import lombok.Value;
import models.DTOs.CartItemDto;
import models.DTOs.UserDto;
import models.entity.ShoppingCart;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link ShoppingCart}
 */
@Value
public class ShoppingCartDto implements Serializable {
    Long id;
    UserDto user;
    List<CartItemDto> cartItems;
    Date lastUpdate;
}