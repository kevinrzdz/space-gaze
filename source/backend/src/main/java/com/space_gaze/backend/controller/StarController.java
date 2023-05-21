package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.Star;
import com.space_gaze.backend.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.core.io.Resource;
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
@RequestMapping("/api/stars")
public class StarController {

    @Autowired
    private StarService starService;

    @GetMapping
    public List<Star> getAllStars(@RequestParam(required = false) String name) {
        return starService.search(name);
    }

    @GetMapping("paged")
    public Page<Star> getAllStars(
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

    @PostMapping("/upload")
    public Star uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
        Star star = starService.findById(id);

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
            String previousImageName = star.getImage();
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
            star.setImage(fileName);
            starService.save(star);
        }

        return star;
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
