package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.Exoplanet;
import com.space_gaze.backend.repository.ExoplanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExoplanetService implements CrudServiceInterface<Exoplanet, Integer> {

    @Autowired
    private ExoplanetRepository exoplanetRepository;

    @Override
    public List<Exoplanet> findAll() {
        return exoplanetRepository.findAll();
    }

    @Override
    public Page<Exoplanet> findAll(Pageable pageable) {
        return exoplanetRepository.findAll(pageable);
    }

    @Override
    public Exoplanet findById(Integer id) {
        return exoplanetRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not found exoplanet with id: " + id));
    }

    @Override
    public Exoplanet save(Exoplanet entity) {
        return exoplanetRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        exoplanetRepository.deleteById(id);
    }
}
