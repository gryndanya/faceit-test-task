package com.techtask.faceittesttask.service;

import com.techtask.faceittesttask.dto.CuisineDto;

import java.util.List;

public interface CuisineService {
    List<CuisineDto> getAllCuisines();
    CuisineDto getCuisineById(Long id);
    CuisineDto getCuisineByName(String name);
    CuisineDto createCuisine(CuisineDto dto);
    CuisineDto updateCuisine(Long id, CuisineDto cuisineDto);
    void deleteCuisineById(Long id);
}
