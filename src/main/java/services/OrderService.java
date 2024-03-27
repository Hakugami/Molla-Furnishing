package services;

import mappers.OrderMapper;
import models.DTOs.OrderDto;
import models.entity.Order;
import persistence.repositories.impl.OrderRepository;

import java.util.List;

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
