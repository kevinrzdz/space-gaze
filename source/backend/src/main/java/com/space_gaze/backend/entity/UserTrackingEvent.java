package com.space_gaze.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_tracking_event")
public class UserTrackingEvent implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private AstronomicalEvent astronomicalEvent;
}

