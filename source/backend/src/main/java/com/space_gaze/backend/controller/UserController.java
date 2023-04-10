package com.space_gaze.backend.controller;

import com.space_gaze.backend.dto.LoginDto;
import com.space_gaze.backend.dto.UserDto;
import com.space_gaze.backend.response.LoginResponse;
import com.space_gaze.backend.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @PostMapping("/save")
    public String saveUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        LoginResponse loginResponse = userService.loginUser(loginDto);
        return ResponseEntity.ok(loginResponse);
    }

}
