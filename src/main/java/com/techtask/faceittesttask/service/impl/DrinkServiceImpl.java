package com.techtask.faceittesttask.service.impl;

import com.techtask.faceittesttask.dto.DrinkDto;
import com.techtask.faceittesttask.exception.ExistsException;
import com.techtask.faceittesttask.exception.NotFoundException;
import com.techtask.faceittesttask.mapper.DrinkMapper;
import com.techtask.faceittesttask.repository.DrinkRepository;
import com.techtask.faceittesttask.service.DrinkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DrinkServiceImpl implements DrinkService {
    private final DrinkRepository DrinkRepository;
    private final DrinkMapper DrinkMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DrinkDto> getAllDrinks() {
        return DrinkRepository.findAll().stream()
                .map(DrinkMapper::mapToDrinkDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DrinkDto getDrinkById(Long id) {
        return DrinkRepository.findById(id)
                .map(DrinkMapper::mapToDrinkDto)
                .orElseThrow(() -> new NotFoundException("Drink with id = " + id + " not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public DrinkDto getDrinkByName(String name) {
        return DrinkRepository.findByName(name)
                .map(DrinkMapper::mapToDrinkDto)
                .orElseThrow(() -> new NotFoundException("Drink with name = " + name + " not found."));
    }

    @Override
    @Transactional
    public DrinkDto createDrink(DrinkDto dto) {
        String DrinkName = dto.getName().trim().toUpperCase();
        if(DrinkRepository.findByName(DrinkName).isEmpty()){
            throw new ExistsException("Drink with name = " + DrinkName + " already exists.");
        }else{
            dto.setName(DrinkName);
            return DrinkMapper.mapToDrinkDto(DrinkRepository.save(DrinkMapper.mapToDrink(dto)));
        }
    }

    @Override
    @Transactional
    public DrinkDto updateDrink(Long id, DrinkDto DrinkDto) {
        return DrinkRepository.findById(id)
                .map(currentDrink -> {
                    currentDrink.setName(DrinkDto.getName().trim().toUpperCase());
                    currentDrink.setPrice(DrinkDto.getPrice());
                    return  DrinkRepository.save(currentDrink);
                })
                .map(DrinkMapper::mapToDrinkDto)
                .orElseThrow(() -> new NotFoundException("Drink with id = " + id + " not found."));
    }

    @Override
    @Transactional
    public void deleteDrinkById(Long id) {
        DrinkRepository.findById(id)
                .ifPresentOrElse(DrinkRepository::delete,
                        () -> {
                            throw new NotFoundException("Drink with id = " + id + " not found.");
                        }
                );
    }
}
