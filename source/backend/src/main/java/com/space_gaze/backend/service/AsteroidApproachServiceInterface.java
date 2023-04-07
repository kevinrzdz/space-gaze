package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.AsteroidApproach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AsteroidApproachServiceInterface {

    public Page<AsteroidApproach> findAll(Pageable pageable);
}
