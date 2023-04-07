package com.space_gaze.backend.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "asteroid_approach")
public class AsteroidApproach {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "asteroid_id", nullable = false)
    private int asteroidId;
    @Basic
    @Column(name = "date", nullable = false)
    private Date date;
    @Basic
    @Column(name = "speed", nullable = false, precision = 0)
    private double speed;
    @ManyToOne
    @JoinColumn(name = "asteroid_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Asteroid asteroidByAsteroidId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAsteroidId() {
        return asteroidId;
    }

    public void setAsteroidId(int asteroidId) {
        this.asteroidId = asteroidId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsteroidApproach that = (AsteroidApproach) o;
        return id == that.id && asteroidId == that.asteroidId && Double.compare(that.speed, speed) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, asteroidId, date, speed);
    }

    public Asteroid getAsteroidByAsteroidId() {
        return asteroidByAsteroidId;
    }

    public void setAsteroidByAsteroidId(Asteroid asteroidByAsteroidId) {
        this.asteroidByAsteroidId = asteroidByAsteroidId;
    }
}
