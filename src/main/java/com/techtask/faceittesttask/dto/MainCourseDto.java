package com.techtask.faceittesttask.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainCourseDto {
    @Size(max = 255)
    @NotBlank(message = "Name must not be null")
    private String name;
    @NotBlank(message = "Price must not be null")
    @Min(0)
    private Double price;
    @Size(max = 255)
    @NotBlank(message = "Cuisine must not be null")
    private String cuisine;
}
