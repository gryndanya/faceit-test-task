package com.techtask.faceittesttask.mapper;

import com.techtask.faceittesttask.dto.DrinkDto;
import com.techtask.faceittesttask.model.Drink;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DrinkMapper {

    Drink mapToDrink(DrinkDto taskDto);
    DrinkDto mapToDrinkDto(Drink task);
}
