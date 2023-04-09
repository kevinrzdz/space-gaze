package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.Asteroid;
import com.space_gaze.backend.repository.AsteroidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AsteroidService implements AsteroidServiceInterface {

    @Autowired
    private AsteroidRepository asteroidDao;

    public Page<Asteroid> findAll(Pageable pageable) {
        return asteroidDao.findAll(pageable);
    }

    @Override
    public Asteroid findById(Integer id) {
        Optional<Asteroid> result = asteroidDao.findById(Math.toIntExact(id));
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Could not found asteroid with id: " + id);
        }
    }

    @Override
    public Asteroid save(Asteroid asteroid) {
        return asteroidDao.save(asteroid);
    }

    @Override
    public void deleteById(Integer id) {
        asteroidDao.deleteById(Math.toIntExact(id));
    }
}
