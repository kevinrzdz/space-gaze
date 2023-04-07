package com.space_gaze.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "astronomical_event")
public class AstronomicalEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy = "astronomicalEvent")
    private List<UserTracking> userTrackings;

    @Serial
    private static final long serialVersionUID = 1L;
}