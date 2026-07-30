package com.space_gaze.backend.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

    @GetMapping(value = {
            "/",
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
    public String forward() {
        return "forward:/index.html";
    }
}
