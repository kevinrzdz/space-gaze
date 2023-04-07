package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.Asteroid;
import com.space_gaze.backend.service.AsteroidServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AsteroidRestController {

    @Autowired
    private AsteroidServiceInterface asteroidService;

    @GetMapping("/asteroids/page/{page}")
    public Page<Asteroid> getAsteroids(@PathVariable int page) {
        return asteroidService.findAll(PageRequest.of(page, 20));
    }
}
