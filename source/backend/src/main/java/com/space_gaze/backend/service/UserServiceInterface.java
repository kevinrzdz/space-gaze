package com.space_gaze.backend.service;

import com.space_gaze.backend.dto.LoginDto;
import com.space_gaze.backend.dto.UserDto;
import com.space_gaze.backend.response.LoginResponse;

public interface UserServiceInterface {

    LoginResponse addUser(UserDto userDto);

    LoginResponse loginUser(LoginDto loginDto);
}
