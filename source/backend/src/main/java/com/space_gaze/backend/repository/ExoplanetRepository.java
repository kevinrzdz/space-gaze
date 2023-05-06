package com.space_gaze.backend.repository;

import com.space_gaze.backend.entity.Exoplanet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExoplanetRepository extends JpaRepository<Exoplanet, Integer> {
    @Query("SELECT e FROM Exoplanet e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Exoplanet> findByName(String name, Pageable pageable);
}
