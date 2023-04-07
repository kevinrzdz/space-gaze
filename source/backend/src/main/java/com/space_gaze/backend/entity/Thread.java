package com.space_gaze.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "thread")
public class Thread implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Lob
    private String content;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "thread")
    private List<Post> posts;

    @Serial
    private static final long serialVersionUID = 1L;
}