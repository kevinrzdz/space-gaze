package com.space_gaze.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

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

    public User(Integer id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
