package com.space_gaze.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "planet")
public class Planet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "diameter", nullable = false, precision = 0)
    private double diameter;
    @Basic
    @Column(name = "distance", nullable = false, precision = 0)
    private double distance;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    @OneToMany(mappedBy = "planetByObjectId")
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

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return id == planet.id && Double.compare(planet.diameter, diameter) == 0 && Double.compare(planet.distance, distance) == 0 && Objects.equals(name, planet.name) && Objects.equals(description, planet.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, diameter, distance, description);
    }

    public Collection<UserTracking> getUserTrackingsById() {
        return userTrackingsById;
    }

    public void setUserTrackingsById(Collection<UserTracking> userTrackingsById) {
        this.userTrackingsById = userTrackingsById;
    }
}
