package com.space_gaze.backend.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_tracking")
public class UserTracking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "object_type", nullable = false, length = 50)
    private String objectType;
    @Basic
    @Column(name = "object_id", nullable = false)
    private int objectId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User userByUserId;
    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Asteroid asteroidByObjectId;
    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private AstronomicalEvent astronomicalEventByObjectId;
    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Exoplanet exoplanetByObjectId;
    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Star starByObjectId;
    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Planet planetByObjectId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTracking that = (UserTracking) o;
        return id == that.id && userId == that.userId && objectId == that.objectId && Objects.equals(objectType, that.objectType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, objectType, objectId);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Asteroid getAsteroidByObjectId() {
        return asteroidByObjectId;
    }

    public void setAsteroidByObjectId(Asteroid asteroidByObjectId) {
        this.asteroidByObjectId = asteroidByObjectId;
    }

    public AstronomicalEvent getAstronomicalEventByObjectId() {
        return astronomicalEventByObjectId;
    }

    public void setAstronomicalEventByObjectId(AstronomicalEvent astronomicalEventByObjectId) {
        this.astronomicalEventByObjectId = astronomicalEventByObjectId;
    }

    public Exoplanet getExoplanetByObjectId() {
        return exoplanetByObjectId;
    }

    public void setExoplanetByObjectId(Exoplanet exoplanetByObjectId) {
        this.exoplanetByObjectId = exoplanetByObjectId;
    }

    public Star getStarByObjectId() {
        return starByObjectId;
    }

    public void setStarByObjectId(Star starByObjectId) {
        this.starByObjectId = starByObjectId;
    }

    public Planet getPlanetByObjectId() {
        return planetByObjectId;
    }

    public void setPlanetByObjectId(Planet planetByObjectId) {
        this.planetByObjectId = planetByObjectId;
    }
}
