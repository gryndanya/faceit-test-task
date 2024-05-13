package com.techtask.faceittesttask.service.impl;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.exception.ExistsException;
import com.techtask.faceittesttask.exception.NotFoundException;
import com.techtask.faceittesttask.mapper.CuisineMapper;
import com.techtask.faceittesttask.repository.CuisineRepository;
import com.techtask.faceittesttask.service.CuisineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CuisineServiceImpl  implements CuisineService {
    private final CuisineRepository cuisineRepository;
    private final CuisineMapper cuisineMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CuisineDto> getAllCuisines() {
        return cuisineRepository.findAll().stream()
                .map(cuisineMapper::mapToCuisineDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CuisineDto getCuisineById(Long id) {
        return cuisineRepository.findById(id)
                .map(cuisineMapper::mapToCuisineDto)
                .orElseThrow(() -> new NotFoundException("Cuisine with id = " + id + " not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public CuisineDto getCuisineByName(String name) {
        return cuisineRepository.findByName(name)
                .map(cuisineMapper::mapToCuisineDto)
                .orElseThrow(() -> new NotFoundException("Cuisine with name = " + name + " not found."));
    }

    @Override
    @Transactional
    public CuisineDto createCuisine(CuisineDto dto) {
        String cuisineName = dto.getName().trim().toUpperCase();
        if(cuisineRepository.findByName(cuisineName).isEmpty()){
            throw new ExistsException("Cuisine with name = " + cuisineName + " already exists.");
        }else{
            dto.setName(cuisineName);
            return cuisineMapper.mapToCuisineDto(cuisineRepository.save(cuisineMapper.mapToCuisine(dto)));
        }
    }

    @Override
    @Transactional
    public CuisineDto updateCuisine(Long id, CuisineDto cuisineDto) {
        return cuisineRepository.findById(id)
                .map(currentCuisine -> {
                    currentCuisine.setName(cuisineDto.getName().trim().toUpperCase());
                    return  cuisineRepository.save(currentCuisine);
                })
                .map(cuisineMapper::mapToCuisineDto)
                .orElseThrow(() -> new NotFoundException("Cuisine with id = " + id + " not found."));
    }

    @Override
    @Transactional
    public void deleteCuisineById(Long id) {
        cuisineRepository.findById(id)
                .ifPresentOrElse(cuisineRepository::delete,
                        () -> {
                            throw new NotFoundException("Cuisine with id = " + id + " not found.");
                        }
                );
    }
}
