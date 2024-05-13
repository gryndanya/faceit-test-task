package com.techtask.faceittesttask.repository;

import com.techtask.faceittesttask.model.Cuisine;
import com.techtask.faceittesttask.model.MainCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MainCourseRepository extends JpaRepository<MainCourse, Long> {
    Optional<MainCourse> findByName(String name);
    List<MainCourse> findByCuisine(Cuisine cuisine);
}
