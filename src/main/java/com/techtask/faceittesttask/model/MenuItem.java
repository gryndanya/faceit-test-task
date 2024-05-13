package com.techtask.faceittesttask.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public abstract class MenuItem {

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    @Min(0)
    private Double price;
}
