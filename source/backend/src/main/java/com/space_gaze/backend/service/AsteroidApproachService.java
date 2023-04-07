package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.AsteroidApproach;
import com.space_gaze.backend.repository.AsteroidApproachDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AsteroidApproachService implements AsteroidApproachServiceInterface {
    @Autowired
    private AsteroidApproachDaoInterface asteroidApproachDao;

    @Override
    public Page<AsteroidApproach> findAll(Pageable pageable) {
        return asteroidApproachDao.findAll(pageable);
    }
}
