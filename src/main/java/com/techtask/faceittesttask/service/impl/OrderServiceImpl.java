package com.techtask.faceittesttask.service.impl;

import com.techtask.faceittesttask.dto.OrderDto;
import com.techtask.faceittesttask.exception.ExistsException;
import com.techtask.faceittesttask.exception.NotFoundException;
import com.techtask.faceittesttask.mapper.OrderMapper;
import com.techtask.faceittesttask.repository.DessertRepository;
import com.techtask.faceittesttask.repository.DrinkRepository;
import com.techtask.faceittesttask.repository.MainCourseRepository;
import com.techtask.faceittesttask.repository.OrderRepository;
import com.techtask.faceittesttask.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository OrderRepository;
    private final MainCourseRepository mainCourseRepository;
    private final DessertRepository dessertRepository;
    private final DrinkRepository drinkRepository;
    private final OrderMapper OrderMapper;
    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        return OrderRepository.findAll().stream()
                .map(OrderMapper::mapToOrderDto)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrderById(Long id) {
        return OrderRepository.findById(id)
                .map(OrderMapper::mapToOrderDto)
                .orElseThrow(() -> new NotFoundException("Order with id = " + id + " not found."));
    }


    @Override
    @Transactional
    public OrderDto createOrder(OrderDto dto) {
        if(mainCourseRepository.findByName(dto.getMainCourse().trim().toUpperCase()).isEmpty() &&
                dessertRepository.findByName(dto.getDessert().trim().toUpperCase()).isEmpty() &&
                drinkRepository.findByName(dto.getDrink().trim().toUpperCase()).isEmpty()){
            throw new ExistsException("Order empty.");
        }else{
            return OrderMapper.mapToOrderDto(OrderRepository.save(OrderMapper.mapToOrder(dto)));
        }
    }

    @Override
    @Transactional
    public OrderDto updateOrder(Long id, OrderDto OrderDto) {
        return OrderRepository.findById(id)
                .map(OrderRepository::save)
                .map(OrderMapper::mapToOrderDto)
                .orElseThrow(() -> new NotFoundException("Order with id = " + id + " not found."));
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) {
        OrderRepository.findById(id)
                .ifPresentOrElse(OrderRepository::delete,
                        () -> {
                            throw new NotFoundException("Order with id = " + id + " not found.");
                        }
                );
    }
}
