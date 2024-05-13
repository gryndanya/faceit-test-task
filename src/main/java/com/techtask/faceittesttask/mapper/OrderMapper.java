package com.techtask.faceittesttask.mapper;

import com.techtask.faceittesttask.dto.OrderDto;
import com.techtask.faceittesttask.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order mapToOrder(OrderDto taskDto);
    OrderDto mapToOrderDto(Order task);
}
