package com.space_gaze.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_tracking_event")
public class UserTrackingEvent {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private AstronomicalEvent astronomicalEvent;
}

