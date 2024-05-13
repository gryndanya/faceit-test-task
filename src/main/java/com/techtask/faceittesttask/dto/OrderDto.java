package com.techtask.faceittesttask.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    @NotBlank(message = "Price must not be null")
    @Min(0)
    private Double totalPrice;
    @Size(max = 255)
    private String mainCourse;
    @Size(max = 255)
    private String dessert;
    @Size(max = 255)
    private String drink;
    private Boolean ice;
    private Boolean lemon;

}
