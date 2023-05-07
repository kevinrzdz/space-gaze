package com.space_gaze.backend.repository;

import com.space_gaze.backend.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Integer> {
}
