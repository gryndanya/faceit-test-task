package com.techtask.faceittesttask.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public abstract class MenuItem {
    @Id
    private Long id;
    private String name;
    private Double price;
}
