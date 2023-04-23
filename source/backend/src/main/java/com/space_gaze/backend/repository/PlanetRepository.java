package com.space_gaze.backend.repository;

import com.space_gaze.backend.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {
}
