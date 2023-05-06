package com.space_gaze.backend.repository;

import com.space_gaze.backend.entity.Asteroid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AsteroidRepository extends JpaRepository<Asteroid, Integer> {
    @Query("SELECT a FROM Asteroid a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Asteroid> findByName(String name, Pageable pageable);
}

