package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.Planet;
import com.space_gaze.backend.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService implements CrudServiceInterface<Planet, Integer> {

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    @Override
    public Page<Planet> findAll(Pageable pageable) {
        return planetRepository.findAll(pageable);
    }

    @Override
    public Planet findById(Integer id) {
        return planetRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not found planet with id: " + id));
    }

    @Override
    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }

    @Override
    public void deleteById(Integer id) {
        planetRepository.deleteById(id);
    }
}