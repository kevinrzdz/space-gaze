package com.space_gaze.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "asteroid_approach")
public class AsteroidApproach implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "asteroid_id")
    private Asteroid asteroid;

    @Temporal(TemporalType.DATE)
    private Date date;

    private double speed;

    @Serial
    private static final long serialVersionUID = 1L;
}