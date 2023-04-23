package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.Star;
import com.space_gaze.backend.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/stars")
public class StarController {

    @Autowired
    private StarService starService;

    @GetMapping
    public Page<Star> getAllAsteroids(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return starService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Star getStarById(@PathVariable Integer id) {
        return starService.findById(id);
    }

    @PostMapping
    public Star createStar(@RequestBody Star star) {
        return starService.save(star);
    }

    @PutMapping("/{id}")
    public Star updateStar(@PathVariable Integer id, @RequestBody Star star) {
        star.setId(id);
        return starService.save(star);
    }

    @DeleteMapping("/{id}")
    public void deleteStar(@PathVariable Integer id) {
        starService.deleteById(id);
    }
}
