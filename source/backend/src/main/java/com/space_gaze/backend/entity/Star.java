package com.space_gaze.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "star")
public class Star {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "constellation", nullable = false, length = 255)
    private String constellation;
    @Basic
    @Column(name = "magnitude", nullable = false, length = 100)
    private String magnitude;
    @Basic
    @Column(name = "distance_earth", nullable = false, length = 100)
    private String distanceEarth;
    @OneToMany(mappedBy = "starByObjectId")
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

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getDistanceEarth() {
        return distanceEarth;
    }

    public void setDistanceEarth(String distanceEarth) {
        this.distanceEarth = distanceEarth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return id == star.id && Objects.equals(name, star.name) && Objects.equals(constellation, star.constellation) && Objects.equals(magnitude, star.magnitude) && Objects.equals(distanceEarth, star.distanceEarth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, constellation, magnitude, distanceEarth);
    }

    public Collection<UserTracking> getUserTrackingsById() {
        return userTrackingsById;
    }

    public void setUserTrackingsById(Collection<UserTracking> userTrackingsById) {
        this.userTrackingsById = userTrackingsById;
    }
}
