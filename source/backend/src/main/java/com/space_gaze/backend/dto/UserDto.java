package com.space_gaze.backend.dto;

import com.space_gaze.backend.entity.User;

public class UserDto {

    private int id;
    private String email;
    private String username;
    private String password;

    private User.Role role;

    public UserDto() {
    }

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

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{" + "id=" + id + ", email='" + email + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", role=" + role + '}';
    }
}