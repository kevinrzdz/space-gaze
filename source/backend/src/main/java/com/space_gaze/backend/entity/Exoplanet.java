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
@Table(name = "exoplanet")
public class Exoplanet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "discovery_year")
    private int discoveryYear;

    @Column(name = "publication_date")
    private String publicationDate;

    @Column(name = "discovery_facility")
    private String discoveryFacility;

    private double mass;

    private double radius;

    @OneToMany(mappedBy = "exoplanet")
    private List<UserTracking> userTrackings;

    @Serial
    private static final long serialVersionUID = 1L;
}