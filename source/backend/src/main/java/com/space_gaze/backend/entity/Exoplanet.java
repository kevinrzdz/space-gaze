package com.space_gaze.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "exoplanet")
public class Exoplanet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "discovery_year")
    private Integer discoveryYear;

    @Column(name = "publication_date")
    private String publicationDate;

    @Column(name = "discovery_facility")
    private String discoveryFacility;

    @Column(name = "mass")
    private Double mass;

    @Column(name = "radius")
    private Double radius;

    @OneToMany(mappedBy = "exoplanet")
    @JsonIgnore
    private List<UserTrackingExoplanet> userTrackingExoplanets;
}


