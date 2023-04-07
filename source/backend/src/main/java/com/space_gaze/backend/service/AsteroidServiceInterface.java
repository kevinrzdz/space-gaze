package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.Asteroid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AsteroidServiceInterface {

    public Page<Asteroid> findAll(Pageable pageable);
}
