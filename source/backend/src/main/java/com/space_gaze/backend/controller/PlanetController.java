package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.Planet;
import com.space_gaze.backend.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @GetMapping
    public List<Planet> getAllPlanets() {
        return planetService.findAll();
    }

    @GetMapping("/{id}")
    public Planet getPlanetById(@PathVariable Integer id) {
        return planetService.findById(id);
    }

    @PostMapping
    public Planet createPlanet(@RequestBody Planet planet) {
        return planetService.save(planet);
    }

    @PutMapping("/{id}")
    public Planet updatePlanet(@PathVariable Integer id, @RequestBody Planet planet) {
        planet.setId(id);
        return planetService.save(planet);
    }

    @DeleteMapping("/{id}")
    public void deletePlanet(@PathVariable Integer id) {
        planetService.deleteById(id);
    }

}
