package com.space_gaze.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "star")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "constellation", nullable = false)
    private String constellation;

    @Column(name = "magnitude", nullable = false)
    private String magnitude;

    @Column(name = "distance_earth", nullable = false)
    private String distanceEarth;

    @OneToMany(mappedBy = "star")
    @JsonIgnore
    private List<UserTrackingStar> userTrackingStars;
}

