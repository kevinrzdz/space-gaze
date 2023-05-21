package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.AstronomicalEvent;
import com.space_gaze.backend.service.AstronomicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/astronomical-events")
public class AstronomicalEventController {

    @Autowired
    private AstronomicalEventService astronomicalEventService;

    @GetMapping
    public List<AstronomicalEvent> getAllEvent() {
        return astronomicalEventService.findAll();
    }

    @GetMapping("/{id}")
    public AstronomicalEvent getEventById(@PathVariable Integer id) {
        return astronomicalEventService.findById(id);
    }

    @PostMapping
    public AstronomicalEvent createEvent(@RequestBody AstronomicalEvent astronomicalEvent) {
        return astronomicalEventService.save(astronomicalEvent);
    }

    @PutMapping("/{id}")
    public AstronomicalEvent updateEvent(@PathVariable Integer id, @RequestBody AstronomicalEvent astronomicalEvent) {
        astronomicalEvent.setId(id);
        return astronomicalEventService.save(astronomicalEvent);

    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        astronomicalEventService.deleteById(id);
    }

}