package com.techtask.faceittesttask.model;

import jakarta.persistence.*;

@Entity
@Table(name = "test.drinks")
public class Drink extends MenuItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;
}
