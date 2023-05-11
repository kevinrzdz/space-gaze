package com.space_gaze.backend.controller;

import com.space_gaze.backend.entity.User;
import com.space_gaze.backend.repository.UserRepository;
import com.space_gaze.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

        // Comprobar si el nombre de usuario o el correo electrónico ya existen
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


}
