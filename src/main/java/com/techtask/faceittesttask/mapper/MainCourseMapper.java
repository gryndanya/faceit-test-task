package com.techtask.faceittesttask.mapper;

import com.techtask.faceittesttask.dto.MainCourseDto;
import com.techtask.faceittesttask.model.MainCourse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainCourseMapper {
    MainCourse mapToMainCourse(MainCourseDto taskDto);
    MainCourseDto mapToMainCourseDto(MainCourse task);
}
