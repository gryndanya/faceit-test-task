package com.techtask.faceittesttask.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "")
public class Cuisine {
    @Id
    private Long id;
    private String name;
}
