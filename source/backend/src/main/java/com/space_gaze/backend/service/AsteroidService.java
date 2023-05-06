package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.Asteroid;
import com.space_gaze.backend.repository.AsteroidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsteroidService implements CrudServiceInterface<Asteroid, Integer> {

    @Autowired
    private AsteroidRepository asteroidRepository;

    @Override
    public List<Asteroid> findAll() {
        return asteroidRepository.findAll();
    }

    @Override
    public Page<Asteroid> findAll(Pageable pageable) {
        return asteroidRepository.findAll(pageable);
    }

    public List<Asteroid> search(String name) {
        List<Asteroid> asteroids = asteroidRepository.findAll();

        if (name == null || name.trim().isEmpty()) {
            return asteroids;
        } else {
            return asteroids.stream()
                    .filter(asteroid -> asteroid.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
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
