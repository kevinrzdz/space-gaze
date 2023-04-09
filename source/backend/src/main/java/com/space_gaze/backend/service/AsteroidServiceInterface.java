package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.Asteroid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AsteroidServiceInterface {

    Page<Asteroid> findAll(Pageable pageable);

    Asteroid findById(Integer id);

    Asteroid save(Asteroid asteroid);

    void deleteById(Integer id);
}
