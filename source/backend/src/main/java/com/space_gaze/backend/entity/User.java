package com.space_gaze.backend.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "role", nullable = false, length = 10)
    private String role;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Post> postsById;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Thread> threadsById;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserTracking> userTrackingsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, role);
    }

    public Collection<Post> getPostsById() {
        return postsById;
    }

    public void setPostsById(Collection<Post> postsById) {
        this.postsById = postsById;
    }

    public Collection<Thread> getThreadsById() {
        return threadsById;
    }

    public void setThreadsById(Collection<Thread> threadsById) {
        this.threadsById = threadsById;
    }

    public Collection<UserTracking> getUserTrackingsById() {
        return userTrackingsById;
    }

    public void setUserTrackingsById(Collection<UserTracking> userTrackingsById) {
        this.userTrackingsById = userTrackingsById;
    }
}
