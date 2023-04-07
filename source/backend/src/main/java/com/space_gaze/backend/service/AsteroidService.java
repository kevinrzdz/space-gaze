package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.Asteroid;
import com.space_gaze.backend.repository.AsteroidDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AsteroidService implements AsteroidServiceInterface {

    @Autowired
    private AsteroidDaoInterface asteroidDao;

    @Override
    public Page<Asteroid> findAll(Pageable pageable) {
        return asteroidDao.findAll(pageable);
    }
}
