package com.space_gaze.backend.repository;

import com.space_gaze.backend.entity.Exoplanet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExoplanetRepository extends JpaRepository<Exoplanet, Integer> {
}
