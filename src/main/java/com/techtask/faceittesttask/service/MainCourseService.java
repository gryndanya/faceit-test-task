package com.techtask.faceittesttask.service;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.dto.MainCourseDto;

import java.util.List;

public interface MainCourseService {
    List<MainCourseDto> getAllMainCourses();
    List<MainCourseDto> getAllMainCoursesByCuisine(CuisineDto cuisine);
    MainCourseDto getMainCourseById(Long id);
    MainCourseDto getMainCourseByName(String name);
    MainCourseDto createMainCourse(MainCourseDto dto);
    MainCourseDto updateMainCourse(Long id, MainCourseDto mainCourseDto);
    void deleteMainCourseById(Long id);
}