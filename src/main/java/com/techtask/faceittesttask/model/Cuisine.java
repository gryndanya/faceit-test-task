package com.techtask.faceittesttask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "test.cuisine_types")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cuisine {
    @Id
    private Long id;

    @Column
    @NotNull
    private String name;

    @OneToMany(mappedBy = "cuisine", cascade = CascadeType.ALL)
    private List<MainCourse> mainCourses;

    @OneToMany(mappedBy = "cuisine", cascade = CascadeType.ALL)
    private List<Dessert> desserts;

    public Cuisine(String italian) {
    }
}
