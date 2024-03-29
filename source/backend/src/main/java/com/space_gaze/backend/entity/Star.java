package com.space_gaze.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "star")
public class Star implements Serializable {
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

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "star")
    @JsonIgnore
    private List<UserTrackingStar> userTrackingStars;
}

