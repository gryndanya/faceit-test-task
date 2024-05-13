package com.techtask.faceittesttask.service.impl;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.dto.MainCourseDto;
import com.techtask.faceittesttask.exception.ExistsException;
import com.techtask.faceittesttask.exception.NotFoundException;
import com.techtask.faceittesttask.mapper.CuisineMapper;
import com.techtask.faceittesttask.mapper.MainCourseMapper;
import com.techtask.faceittesttask.repository.MainCourseRepository;
import com.techtask.faceittesttask.service.MainCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MainCourseServiceImpl implements MainCourseService {
    private final MainCourseRepository MainCourseRepository;
    private final MainCourseMapper MainCourseMapper;
    private final CuisineMapper cuisineMapper;
    @Override
    @Transactional(readOnly = true)
    public List<MainCourseDto> getAllMainCourses() {
        return MainCourseRepository.findAll().stream()
                .map(MainCourseMapper::mapToMainCourseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MainCourseDto> getAllMainCoursesByCuisine(CuisineDto cuisineDto) {
        return MainCourseRepository.findByCuisine(cuisineMapper.mapToCuisine(cuisineDto)).stream()
                .map(MainCourseMapper::mapToMainCourseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MainCourseDto getMainCourseById(Long id) {
        return MainCourseRepository.findById(id)
                .map(MainCourseMapper::mapToMainCourseDto)
                .orElseThrow(() -> new NotFoundException("MainCourse with id = " + id + " not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public MainCourseDto getMainCourseByName(String name) {
        return MainCourseRepository.findByName(name)
                .map(MainCourseMapper::mapToMainCourseDto)
                .orElseThrow(() -> new NotFoundException("MainCourse with name = " + name + " not found."));
    }

    @Override
    @Transactional
    public MainCourseDto createMainCourse(MainCourseDto dto) {
        String MainCourseName = dto.getName().trim().toUpperCase();
        if(MainCourseRepository.findByName(MainCourseName).isEmpty()){
            throw new ExistsException("MainCourse with name = " + MainCourseName + " already exists.");
        }else{
            dto.setName(MainCourseName);
            return MainCourseMapper.mapToMainCourseDto(MainCourseRepository.save(MainCourseMapper.mapToMainCourse(dto)));
        }
    }

    @Override
    @Transactional
    public MainCourseDto updateMainCourse(Long id, MainCourseDto MainCourseDto) {
        return MainCourseRepository.findById(id)
                .map(currentMainCourse -> {
                    currentMainCourse.setName(MainCourseDto.getName().trim().toUpperCase());
                    currentMainCourse.setPrice(MainCourseDto.getPrice());
                    return  MainCourseRepository.save(currentMainCourse);
                })
                .map(MainCourseMapper::mapToMainCourseDto)
                .orElseThrow(() -> new NotFoundException("MainCourse with id = " + id + " not found."));
    }

    @Override
    @Transactional
    public void deleteMainCourseById(Long id) {
        MainCourseRepository.findById(id)
                .ifPresentOrElse(MainCourseRepository::delete,
                        () -> {
                            throw new NotFoundException("MainCourse with id = " + id + " not found.");
                        }
                );
    }
}