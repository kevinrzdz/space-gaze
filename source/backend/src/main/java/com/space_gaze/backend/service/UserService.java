package com.space_gaze.backend.service;

import com.space_gaze.backend.dto.LoginDto;
import com.space_gaze.backend.dto.UserDto;
import com.space_gaze.backend.entity.User;
import com.space_gaze.backend.repository.UserRepository;
import com.space_gaze.backend.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDto userDto) {
        User user;
        if (userDto.getRole() == null) {
            user = new User(userDto.getId(), userDto.getEmail(), userDto.getUsername(), this.passwordEncoder.encode(userDto.getPassword()));
        } else {
            user = new User(userDto.getId(), userDto.getEmail(), userDto.getUsername(), this.passwordEncoder.encode(userDto.getPassword()), userDto.getRole());
        }

        userRepository.save(user);
        return "User " + user.getUsername() + " added";
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        User user1 = userRepository.findByUsername(loginDto.getUsername());
        if (user1 == null) {
            return new LoginResponse("Email does not exists", false);
        }

        String password = loginDto.getPassword();
        String encodedPassword = user1.getPassword();
        boolean isPasswordRight = passwordEncoder.matches(password, encodedPassword);

        if (!isPasswordRight) {
            return new LoginResponse("Password does not match", false);
        }

        Optional<User> user = userRepository.findOneByUsernameAndPassword(loginDto.getUsername(), encodedPassword);

        if (user.isEmpty()) {
            return new LoginResponse("Login failed", false);
        }

        return new LoginResponse("Login success", true);
    }

}
