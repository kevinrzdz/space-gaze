package com.space_gaze.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_tracking_exoplanet")
public class UserTrackingExoplanet {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "exoplanet_id", nullable = false)
    private Exoplanet exoplanet;
}

