package com.techtask.faceittesttask.service;

import com.techtask.faceittesttask.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    OrderDto createOrder(OrderDto dto);
    OrderDto updateOrder(Long id, OrderDto OrderDto);
    void deleteOrderById(Long id);
}
