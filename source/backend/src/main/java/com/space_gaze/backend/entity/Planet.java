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
@Table(name = "planet")
public class Planet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double diameter;

    private double distanceEarth;

    private String description;

    @OneToMany(mappedBy = "planet")
    private List<UserTracking> userTrackings;

    @Serial
    private static final long serialVersionUID = 1L;
}