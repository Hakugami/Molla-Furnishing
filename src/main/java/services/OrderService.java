package services;

import jakarta.servlet.http.HttpServletRequest;
import mappers.OrderMapper;
import mappers.UserMapper;
import models.DTOs.AddressDto;
import models.DTOs.OrderDto;
import models.DTOs.UserDto;
import models.entity.Address;
import models.entity.Order;
import models.entity.User;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.impl.OrderRepository;
import persistence.repositories.impl.UserRepository;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper orderMapper;

    public OrderService() {
        this.repository = new OrderRepository();
        this.orderMapper = OrderMapper.INSTANCE;
    }

    public List<OrderDto> retrieveOrdersByUserId(Long userId) {
        List<Order> orders = repository.retrieveOrdersByUserId(userId);
        return orders.stream().map(orderMapper::orderToOrderDto).toList();
    }
}
