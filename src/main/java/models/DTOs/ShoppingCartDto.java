package models.DTOs;

import lombok.Value;
import models.entity.ShoppingCart;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * DTO for {@link ShoppingCart}
 */
@Value
public class ShoppingCartDto implements Serializable {
    Long id;
    UserDto user;
    List<ProductDto> products;
    Date lastUpdate;
}