package com.space_gaze.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "asteroid")
public class Asteroid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double diameter;

    private boolean dangerous;

    @OneToMany(mappedBy = "asteroid")
    @JsonIgnore
    private List<AsteroidApproach> asteroidApproaches;

    @OneToMany(mappedBy = "asteroid")
    @JsonIgnore
    private List<UserTracking> userTrackings;

    @Serial
    private static final long serialVersionUID = 1L;
}