package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.User;
import com.space_gaze.backend.repository.UserRepository;
import com.space_gaze.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registro(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encodedPassword);

        boolean existingEmail = userRepository.findByEmail(user.getEmail()).isPresent();
        boolean existingUser = userRepository.findByUsername(user.getUsername()).isPresent();
        if (existingEmail) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Email already exists"), HttpStatus.BAD_REQUEST);
        }
        if (existingUser) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Username already exists"), HttpStatus.BAD_REQUEST);
        }

        userService.save(newUser);
        return new ResponseEntity<>(Collections.singletonMap("message", "User saved successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/api/user")
    public User userData(@RequestParam String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @PostMapping("/api/user/upload")
    public User uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
        User user = userService.findById(id);

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
            String previousImageName = user.getImage();
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
            user.setImage(fileName);
            userService.save(user);
        }

        return user;
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
