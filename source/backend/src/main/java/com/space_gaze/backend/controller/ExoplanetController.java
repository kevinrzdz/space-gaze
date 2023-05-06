package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.Exoplanet;
import com.space_gaze.backend.service.ExoplanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/exoplanets")
public class ExoplanetController {

    @Autowired
    private ExoplanetService exoplanetService;

    @GetMapping
    public List<Exoplanet> getAllExoplanets(@RequestParam(required = false) String name) {
        return exoplanetService.search(name);
    }

    @GetMapping("paged")
    public Page<Exoplanet> getAllAsteroids(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return exoplanetService.findAll(pageable);
    }

    @PostMapping
    public Exoplanet createStar(@RequestBody Exoplanet exoplanet) {
        return exoplanetService.save(exoplanet);
    }

    @PutMapping("/{id}")
    public Exoplanet updateStar(@PathVariable Integer id, @RequestBody Exoplanet exoplanet) {
        exoplanet.setId(id);
        return exoplanetService.save(exoplanet);
    }

    @DeleteMapping("/{id}")
    public void deleteStar(@PathVariable Integer id) {
        exoplanetService.deleteById(id);
    }
}
