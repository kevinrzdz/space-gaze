package com.space_gaze.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_tracking_asteroid")
public class UserTrackingAsteroid {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "asteroid_id", nullable = false)
    private Asteroid asteroid;
}

