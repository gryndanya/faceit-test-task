package com.techtask.faceittesttask.service.impl;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.exception.ExistsException;
import com.techtask.faceittesttask.exception.NotFoundException;
import com.techtask.faceittesttask.mapper.CuisineMapper;
import com.techtask.faceittesttask.model.Cuisine;
import com.techtask.faceittesttask.repository.CuisineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CuisineServiceImplTest {

    @Mock
    private CuisineRepository cuisineRepository;

    @Mock
    private CuisineMapper cuisineMapper;

    @InjectMocks
    private CuisineServiceImpl cuisineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCuisineById_shouldReturnMappedDto() {
        Cuisine cuisine = new Cuisine("Italian");
        CuisineDto expectedDto = new CuisineDto("Italian");
        when(cuisineRepository.findById(1L)).thenReturn(Optional.of(cuisine));
        when(cuisineMapper.mapToCuisineDto(cuisine)).thenReturn(expectedDto);

        CuisineDto cuisineDto = cuisineService.getCuisineById(1L);

        assertEquals(expectedDto, cuisineDto);
    }

    @Test
    public void testGetCuisineByName_shouldThrowNotFoundException() {
        when(cuisineRepository.findByName("Italian")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> cuisineService.getCuisineByName("Italian"));
    }

    @Test
    public void testGetCuisineByName_shouldReturnMappedDto() {
        Cuisine cuisine = new Cuisine("Italian");
        CuisineDto expectedDto = new CuisineDto("Italian");
        when(cuisineRepository.findByName("Italian")).thenReturn(Optional.of(cuisine));
        when(cuisineMapper.mapToCuisineDto(cuisine)).thenReturn(expectedDto);

        CuisineDto cuisineDto = cuisineService.getCuisineByName("Italian");

        assertEquals(expectedDto, cuisineDto);
    }

    @Test
    public void testCreateCuisine_shouldThrowExistsException() {
        CuisineDto dto = new CuisineDto("Italian");
        when(cuisineRepository.findByName(dto.getName().toUpperCase())).thenReturn(Optional.of(new Cuisine()));

        assertThrows(ExistsException.class, () -> cuisineService.createCuisine(dto));
    }

    @Test
    public void testCreateCuisine_shouldReturnMappedDto() {
        // Mock repository behavior
        CuisineDto dto = new CuisineDto("Italian");
        Cuisine savedCuisine = new Cuisine(dto.getName());
        CuisineDto expectedDto = new CuisineDto("ITALIAN"); // Name should be uppercase
        when(cuisineRepository.findByName(dto.getName().toUpperCase())).thenReturn(Optional.empty());
        when(cuisineMapper.mapToCuisine(dto)).thenReturn(savedCuisine);
        when(cuisineRepository.save(savedCuisine)).thenReturn(savedCuisine);
        when(cuisineMapper.mapToCuisineDto(savedCuisine)).thenReturn(expectedDto);

        CuisineDto createdDto = cuisineService.createCuisine(dto);

        assertEquals(expectedDto, createdDto);
    }
}
