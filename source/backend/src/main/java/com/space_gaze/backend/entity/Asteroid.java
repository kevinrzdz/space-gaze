package com.space_gaze.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "asteroid")
public class Asteroid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "diameter", nullable = false)
    private Double diameter;

    @Column(name = "dangerous", nullable = false)
    private Boolean dangerous;

    @OneToMany(mappedBy = "asteroid")
    @JsonIgnore
    private List<UserTrackingAsteroid> userTrackingAsteroids;
}
