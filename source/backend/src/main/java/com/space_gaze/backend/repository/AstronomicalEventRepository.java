package com.space_gaze.backend.repository;

import com.space_gaze.backend.entity.AstronomicalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AstronomicalEventRepository extends JpaRepository<AstronomicalEvent, Integer> {
}
