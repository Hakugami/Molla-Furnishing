package models.DTOs;

import lombok.Value;
import models.DTOs.OrderItemDto;
import models.DTOs.UserDto;
import models.entity.Order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@Value
public class OrderDto implements Serializable {
    Long id;
    UserDto user;
    List<OrderItemDto> orderItems;
    double totalAmount;
    Date orderDate;
}