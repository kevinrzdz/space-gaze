package com.space_gaze.backend.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "asteroid")
public class Asteroid {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "diameter", nullable = false, precision = 0)
    private double diameter;
    @Basic
    @Column(name = "dangerous", nullable = false)
    private boolean dangerous;
    @OneToMany(mappedBy = "asteroidByAsteroidId")
    private Collection<AsteroidApproach> asteroidApproachesById;
    @OneToMany(mappedBy = "asteroidByObjectId")
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

    public boolean isDangerous() {
        return dangerous;
    }

    public void setDangerous(boolean dangerous) {
        this.dangerous = dangerous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asteroid asteroid = (Asteroid) o;
        return id == asteroid.id && Double.compare(asteroid.diameter, diameter) == 0 && dangerous == asteroid.dangerous && Objects.equals(name, asteroid.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, diameter, dangerous);
    }

    public Collection<AsteroidApproach> getAsteroidApproachesById() {
        return asteroidApproachesById;
    }

    public void setAsteroidApproachesById(Collection<AsteroidApproach> asteroidApproachesById) {
        this.asteroidApproachesById = asteroidApproachesById;
    }

    public Collection<UserTracking> getUserTrackingsById() {
        return userTrackingsById;
    }

    public void setUserTrackingsById(Collection<UserTracking> userTrackingsById) {
        this.userTrackingsById = userTrackingsById;
    }
}
