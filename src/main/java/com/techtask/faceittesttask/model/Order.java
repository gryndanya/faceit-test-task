package com.techtask.faceittesttask.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "test.orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<MainCourse> mainCourses;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Dessert> desserts;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Drink> drinks;

    private Boolean ice;

    private Boolean lemon;
}
