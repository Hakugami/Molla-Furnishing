package models.DTOs;

import lombok.*;
import models.entity.CartItem;

import java.io.Serializable;

/**
 * DTO for {@link CartItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CartItemDto implements Serializable {
    ProductDto product;
    int quantity;
}