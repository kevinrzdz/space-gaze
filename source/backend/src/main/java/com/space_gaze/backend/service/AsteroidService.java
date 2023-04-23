package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.Asteroid;
import com.space_gaze.backend.repository.AsteroidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsteroidService implements CrudServiceInterface<Asteroid, Integer> {

    @Autowired
    private AsteroidRepository asteroidRepository;

    @Override
    public List<Asteroid> findAll() {
        return asteroidRepository.findAll();
    }

    public Page<Asteroid> findAll(Pageable pageable) {
        return asteroidRepository.findAll(pageable);
    }

    @Override
    public Asteroid findById(Integer id) {
        return asteroidRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not found asteroid with id: " + id));
    }

    @Override
    public Asteroid save(Asteroid asteroid) {
        return asteroidRepository.save(asteroid);
    }

    @Override
    public void deleteById(Integer id) {
        asteroidRepository.deleteById(id);
    }
}
