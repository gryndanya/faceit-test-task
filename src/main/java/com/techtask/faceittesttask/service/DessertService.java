package com.techtask.faceittesttask.service;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.dto.DessertDto;

import java.util.List;

public interface DessertService {
    List<DessertDto> getAllDesserts();
    List<DessertDto> getAllDessertsByCuisine(CuisineDto cuisineDto);
    DessertDto getDessertById(Long id);
    DessertDto getDessertByName(String name);
    DessertDto createDessert(DessertDto dto);
    DessertDto updateDessert(Long id, DessertDto dessertDto);
    void deleteDessertById(Long id);
}
