package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.Asteroid;
import com.space_gaze.backend.service.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/asteroids")
public class AsteroidController {

    @Autowired
    private AsteroidService asteroidService;

    @GetMapping
    public Page<Asteroid> getAllAsteroids(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return asteroidService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Asteroid getAsteroidById(@PathVariable Integer id) {
        return asteroidService.findById(id);
    }

    @PostMapping("/uploads")
    public Asteroid uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
        Asteroid asteroid = asteroidService.findById(id);

        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + '_' + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "");
            Path uploadsPath = Paths.get("uploads");
            Path filePath = uploadsPath.resolve(fileName).toAbsolutePath();
            try {
                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, filePath);
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String previousImageName = asteroid.getImage();
            if (previousImageName != null && previousImageName.length() > 0) {
                Path previousImagePath = uploadsPath.resolve(previousImageName).toAbsolutePath();
                Path previousImage = previousImagePath.toAbsolutePath();
                if (Files.exists(previousImage) && Files.isReadable(previousImage)) {
                    try {
                        Files.delete(previousImage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            asteroid.setImage(fileName);
            asteroidService.save(asteroid);
        }

        return asteroid;
    }

    @GetMapping("/uploads/img/{imageName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        Path imagePath = Paths.get("uploads").resolve(imageName).toAbsolutePath();
        Resource resource;
        try {
            resource = new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        if (!resource.exists() && !resource.isReadable()) {
            throw new RuntimeException("Image " + imageName + " could not be loaded");
        }
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"");
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }

}
