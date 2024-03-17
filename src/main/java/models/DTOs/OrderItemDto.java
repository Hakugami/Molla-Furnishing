package models.DTOs;

import lombok.Value;
import models.DTOs.ProductDto;
import models.entity.OrderItem;

import java.io.Serializable;

/**
 * DTO for {@link OrderItem}
 */
@Value
public class OrderItemDto implements Serializable {
    Long id;
    ProductDto product;
    int quantity;
    OrderDto order;
}