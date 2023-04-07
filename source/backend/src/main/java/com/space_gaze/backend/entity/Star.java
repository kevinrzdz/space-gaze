package com.space_gaze.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "star")
public class Star implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String constellation;

    private String magnitude;

    @Column(name = "distance_earth")
    private String distanceEarth;

    @OneToMany(mappedBy = "star")
    private List<UserTracking> userTrackings;

    @Serial
    private static final long serialVersionUID = 1L;
}