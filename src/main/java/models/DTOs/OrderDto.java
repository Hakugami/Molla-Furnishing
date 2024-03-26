package models.DTOs;

import lombok.*;
import models.entity.Order;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Order}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder(toBuilder = true)
public class OrderDto implements Serializable {
    Long id;
//    UserDto user;
    List<OrderItemDto> orderItems;
    double totalAmount;
    LocalDate date;
    LocalTime time;
    AddressDto address;
}