package com.techtask.faceittesttask.service.impl;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.dto.DessertDto;
import com.techtask.faceittesttask.exception.ExistsException;
import com.techtask.faceittesttask.exception.NotFoundException;
import com.techtask.faceittesttask.mapper.CuisineMapper;
import com.techtask.faceittesttask.mapper.DessertMapper;
import com.techtask.faceittesttask.repository.DessertRepository;
import com.techtask.faceittesttask.service.DessertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DessertServiceImpl implements DessertService {
    private final DessertRepository dessertRepository;
    private final DessertMapper dessertMapper;
    private final CuisineMapper cuisineMapper;
    @Override
    @Transactional(readOnly = true)
    public List<DessertDto> getAllDesserts() {
        return dessertRepository.findAll().stream()
                .map(dessertMapper::mapToDessertDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DessertDto> getAllDessertsByCuisine(CuisineDto cuisineDto) {
        return dessertRepository.findByCuisine(cuisineMapper.mapToCuisine(cuisineDto)).stream()
                .map(dessertMapper::mapToDessertDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DessertDto getDessertById(Long id) {
        return dessertRepository.findById(id)
                .map(dessertMapper::mapToDessertDto)
                .orElseThrow(() -> new NotFoundException("Dessert with id = " + id + " not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public DessertDto getDessertByName(String name) {
        return dessertRepository.findByName(name)
                .map(dessertMapper::mapToDessertDto)
                .orElseThrow(() -> new NotFoundException("Dessert with name = " + name + " not found."));
    }

    @Override
    @Transactional
    public DessertDto createDessert(DessertDto dto) {
        String dessertName = dto.getName().trim().toUpperCase();
        if(dessertRepository.findByName(dessertName).isEmpty()){
            throw new ExistsException("Dessert with name = " + dessertName + " already exists.");
        }else{
            dto.setName(dessertName);
            return dessertMapper.mapToDessertDto(dessertRepository.save(dessertMapper.mapToDessert(dto)));
        }
    }

    @Override
    @Transactional
    public DessertDto updateDessert(Long id, DessertDto dessertDto) {
        return dessertRepository.findById(id)
                .map(currentDessert -> {
                    currentDessert.setName(dessertDto.getName().trim().toUpperCase());
                    currentDessert.setPrice(dessertDto.getPrice());
                    return  dessertRepository.save(currentDessert);
                })
                .map(dessertMapper::mapToDessertDto)
                .orElseThrow(() -> new NotFoundException("Dessert with id = " + id + " not found."));
    }

    @Override
    @Transactional
    public void deleteDessertById(Long id) {
        dessertRepository.findById(id)
                .ifPresentOrElse(dessertRepository::delete,
                        () -> {
                            throw new NotFoundException("Dessert with id = " + id + " not found.");
                        }
                );
    }
}
