package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.AstronomicalEvent;
import com.space_gaze.backend.service.AstronomicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/astronomical-event")
public class AstronomicalEventController {

    @Autowired
    private AstronomicalEventService astronomicalEventService;

    @GetMapping
    public Page<AstronomicalEvent> getAllAsteroids(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return astronomicalEventService.findAll(pageable);
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
