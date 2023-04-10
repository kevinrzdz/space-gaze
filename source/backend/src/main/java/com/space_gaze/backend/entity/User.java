package com.space_gaze.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<UserTrackingAsteroid> userTrackingAsteroids;

    @OneToMany(mappedBy = "user")
    private List<UserTrackingEvent> userTrackingEvents;

    @OneToMany(mappedBy = "user")
    private List<UserTrackingExoplanet> userTrackingExoplanets;

    @OneToMany(mappedBy = "user")
    private List<UserTrackingPlanet> userTrackingPlanets;

    @OneToMany(mappedBy = "user")
    private List<UserTrackingStar> userTrackingStars;

    public User(int id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = Role.user;
    }

    public User (int id, String email, String username, String password, Role role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public enum Role {
        user, admin
    }
}
