package com.techtask.faceittesttask.repository;

import com.techtask.faceittesttask.model.Cuisine;
import com.techtask.faceittesttask.model.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DessertRepository extends JpaRepository<Dessert, Long> {
    Optional<Dessert> findByName(String name);

    List<Dessert> findByCuisine(Cuisine cuisine);
}