package com.techtask.faceittesttask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuisineDto {

    @NotBlank(message = "Name of cuisine must not be null")
    @Size(max = 255)
    private String name;
}
