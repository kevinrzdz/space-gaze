package com.space_gaze.backend.security;

import lombok.Data;

@Data
public class AuthCredentials {

    private String email;
    private String password;

}
