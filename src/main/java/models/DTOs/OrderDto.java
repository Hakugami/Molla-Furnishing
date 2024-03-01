package models.DTOs;

import lombok.Value;
import models.entity.Order;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@Value
public class OrderDto implements Serializable {
    Long id;
    UserDto user;
    List<ProductDto> products;
    double totalAmount;
    Date orderDate;
}