package com.space_gaze.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "diameter", nullable = false)
    private Double diameter;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "planet")
    @JsonIgnore
    private List<UserTrackingPlanet> userTrackingPlanets;
}

