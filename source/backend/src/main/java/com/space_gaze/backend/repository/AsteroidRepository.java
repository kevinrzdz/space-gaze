package com.space_gaze.backend.repository;

import com.space_gaze.backend.entity.Asteroid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsteroidRepository extends JpaRepository<Asteroid, Integer> {
}

