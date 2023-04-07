package com.space_gaze.backend.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "exoplanet")
public class Exoplanet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "discovery_year", nullable = true)
    private Integer discoveryYear;
    @Basic
    @Column(name = "publication_date", nullable = true, length = 10)
    private String publicationDate;
    @Basic
    @Column(name = "discovery_facility", nullable = true, length = 255)
    private String discoveryFacility;
    @Basic
    @Column(name = "mass", nullable = true, precision = 0)
    private Double mass;
    @Basic
    @Column(name = "radius", nullable = true, precision = 0)
    private Double radius;
    @OneToMany(mappedBy = "exoplanetByObjectId")
    private Collection<UserTracking> userTrackingsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDiscoveryYear() {
        return discoveryYear;
    }

    public void setDiscoveryYear(Integer discoveryYear) {
        this.discoveryYear = discoveryYear;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDiscoveryFacility() {
        return discoveryFacility;
    }

    public void setDiscoveryFacility(String discoveryFacility) {
        this.discoveryFacility = discoveryFacility;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exoplanet exoplanet = (Exoplanet) o;
        return id == exoplanet.id && Objects.equals(name, exoplanet.name) && Objects.equals(discoveryYear, exoplanet.discoveryYear) && Objects.equals(publicationDate, exoplanet.publicationDate) && Objects.equals(discoveryFacility, exoplanet.discoveryFacility) && Objects.equals(mass, exoplanet.mass) && Objects.equals(radius, exoplanet.radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, discoveryYear, publicationDate, discoveryFacility, mass, radius);
    }

    public Collection<UserTracking> getUserTrackingsById() {
        return userTrackingsById;
    }

    public void setUserTrackingsById(Collection<UserTracking> userTrackingsById) {
        this.userTrackingsById = userTrackingsById;
    }
}
