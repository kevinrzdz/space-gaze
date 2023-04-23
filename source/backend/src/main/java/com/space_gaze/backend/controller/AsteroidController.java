package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.Asteroid;
import com.space_gaze.backend.service.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/asteroids")
public class AsteroidController {

    @Autowired
    private AsteroidService asteroidService;

    @GetMapping
    public Page<Asteroid> getAllAsteroids(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return asteroidService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Asteroid getAsteroidById(@PathVariable Integer id) {
        return asteroidService.findById(id);
    }

    @PostMapping
    public Asteroid createAsteroid(@RequestBody Asteroid asteroid) {
        return asteroidService.save(asteroid);
    }

    @PutMapping("/{id}")
    public Asteroid updateAsteroid(@PathVariable Integer id, @RequestBody Asteroid asteroid) {
        asteroid.setId(id);
        return asteroidService.save(asteroid);
    }

    @DeleteMapping("/{id}")
    public void deleteAsteroid(@PathVariable Integer id) {
        asteroidService.deleteById(id);
    }
}
