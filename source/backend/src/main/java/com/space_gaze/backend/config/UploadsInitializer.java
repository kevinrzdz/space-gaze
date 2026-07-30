package com.space_gaze.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadsInitializer implements ApplicationRunner {

    @Value("${app.uploads.dir:uploads}")
    private String uploadsDir;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Path uploadsPath = Paths.get(uploadsDir).toAbsolutePath();
        if (!Files.exists(uploadsPath)) {
            Files.createDirectories(uploadsPath);
        }
    }
}
