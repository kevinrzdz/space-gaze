package com.space_gaze.backend.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

    private static final Resource INDEX = new ClassPathResource("static/index.html");

    @GetMapping(value = {
            "/home",
            "/login",
            "/register",
            "/tracker",
            "/planets",
            "/events",
            "/profile",
            "/asteroids/**",
            "/stars/**",
            "/exoplanets/**"
    })
    public ResponseEntity<Resource> spaRoutes() {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(INDEX);
    }
}
