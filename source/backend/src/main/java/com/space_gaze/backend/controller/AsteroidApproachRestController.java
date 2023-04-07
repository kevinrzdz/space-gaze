package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.AsteroidApproach;
import com.space_gaze.backend.service.AsteroidApproachServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AsteroidApproachRestController {

    @Autowired
    private AsteroidApproachServiceInterface asteroidApproachService;

    @GetMapping("/approaches/page/{page}")
    public Page<AsteroidApproach> getAsteroidApproaches(@PathVariable int page) {
        return asteroidApproachService.findAll(PageRequest.of(page, 20));
    }
}
