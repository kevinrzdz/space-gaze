package com.space_gaze.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user_tracking")
public class UserTracking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @Column(name = "object_type")
    private String objectType;

    @ManyToOne
    private Asteroid asteroid;

    @ManyToOne
    private AstronomicalEvent astronomicalEvent;

    @ManyToOne
    private Exoplanet exoplanet;

    @ManyToOne
    private Planet planet;

    @ManyToOne
    private Star star;

    @Serial
    private static final long serialVersionUID = 1L;
}