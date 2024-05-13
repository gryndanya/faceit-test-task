package com.techtask.faceittesttask.mapper;

import com.techtask.faceittesttask.dto.CuisineDto;
import com.techtask.faceittesttask.model.Cuisine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CuisineMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "nameToUpperCase")
    Cuisine mapToCuisine(CuisineDto taskDto);

    @Named("nameToUpperCase")
    public static String nameToUpperCase(String name) {
        return name.trim().toUpperCase();
    }

    CuisineDto mapToCuisineDto(Cuisine task);
}
