package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.Exoplanet;
import com.space_gaze.backend.service.ExoplanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

@CrossOrigin("*")
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
    public Page<Exoplanet> getAllExoplanets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return exoplanetService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Exoplanet getExoplanetById(@PathVariable Integer id) {
        return exoplanetService.findById(id);
    }

    @PostMapping
    public Exoplanet createExoplanet(@RequestBody Exoplanet exoplanet) {
        return exoplanetService.save(exoplanet);
    }

    @PutMapping("/{id}")
    public Exoplanet updateExoplanet(@PathVariable Integer id, @RequestBody Exoplanet exoplanet) {
        exoplanet.setId(id);
        return exoplanetService.save(exoplanet);
    }

    @DeleteMapping("/{id}")
    public void deleteExoplanet(@PathVariable Integer id) {
        exoplanetService.deleteById(id);
    }

    @PostMapping("/upload")
    public Exoplanet uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
        Exoplanet exoplanet = exoplanetService.findById(id);

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
            String previousImageName = exoplanet.getImage();
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
            exoplanet.setImage(fileName);
            exoplanetService.save(exoplanet);
        }

        return exoplanet;
    }

    @GetMapping("img/{imageName:.+}")
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
