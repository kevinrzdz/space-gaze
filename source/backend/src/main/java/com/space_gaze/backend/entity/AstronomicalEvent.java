package com.space_gaze.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "astronomical_event")
public class AstronomicalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy = "astronomicalEvent")
    @JsonIgnore
    private List<UserTrackingEvent> userTrackingEvents;
}

