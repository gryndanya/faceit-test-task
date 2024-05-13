package com.techtask.faceittesttask.mapper;

import com.techtask.faceittesttask.dto.DessertDto;
import com.techtask.faceittesttask.model.Dessert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DessertMapper {

    Dessert mapToDessert(DessertDto taskDto);
    DessertDto mapToDessertDto(Dessert task);
}
