package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.AstronomicalEvent;
import com.space_gaze.backend.service.AstronomicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/astronomical-event")
public class AstronomicalEventController {

    @Autowired
    private AstronomicalEventService astronomicalEventService;

    @GetMapping
    public List<AstronomicalEvent> getAllAsteroids() {
        return astronomicalEventService.findAll();
    }

    @GetMapping("/{id}")
    public AstronomicalEvent getAsteroidById(@PathVariable Integer id) {
        return astronomicalEventService.findById(id);
    }

    @PostMapping
    public AstronomicalEvent createAsteroid(@RequestBody AstronomicalEvent astronomicalEvent) {
        return astronomicalEventService.save(astronomicalEvent);
    }

    @PutMapping("/{id}")
    public AstronomicalEvent updateAsteroid(@PathVariable Integer id, @RequestBody AstronomicalEvent astronomicalEvent) {
        astronomicalEvent.setId(id);
        return astronomicalEventService.save(astronomicalEvent);

    }

    @DeleteMapping("/{id}")
    public void deleteAsteroid(@PathVariable Integer id) {
        astronomicalEventService.deleteById(id);
    }
}