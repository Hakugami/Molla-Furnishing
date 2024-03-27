package models.DTOs;

import lombok.Value;
import models.entity.OrderItem;
import models.entity.OrderItemId;

import java.io.Serializable;

/**
 * DTO for {@link OrderItem}
 */
@Value
public class OrderItemDto implements Serializable {
    OrderItemId id;
    ProductDto product;
    int quantity;
//    OrderDto order;
}