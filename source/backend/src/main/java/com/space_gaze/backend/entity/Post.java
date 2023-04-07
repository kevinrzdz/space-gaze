package com.space_gaze.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Thread thread;

    @Serial
    private static final long serialVersionUID = 1L;
}