package com.techtask.faceittesttask.service;

import com.techtask.faceittesttask.dto.DrinkDto;

import java.util.List;

public interface DrinkService {
    List<DrinkDto> getAllDrinks();
    DrinkDto getDrinkById(Long id);
    DrinkDto getDrinkByName(String name);
    DrinkDto createDrink(DrinkDto dto);
    DrinkDto updateDrink(Long id, DrinkDto DrinkDto);
    void deleteDrinkById(Long id);
}
