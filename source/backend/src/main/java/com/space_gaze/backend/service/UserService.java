package com.space_gaze.backend.service;

import com.space_gaze.backend.dto.LoginDto;
import com.space_gaze.backend.dto.UserDto;
import com.space_gaze.backend.entity.User;
import com.space_gaze.backend.repository.UserRepository;
import com.space_gaze.backend.response.LoginResponse;
import org.apache.commons.validator.routines.EmailValidator;
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
    public LoginResponse addUser(UserDto userDto) {
        User user;

        if (userRepository.findByEmail(userDto.getEmail().toLowerCase()) != null) {
            return new LoginResponse("Email already in use", false);
        }

        if (!EmailValidator.getInstance().isValid(userDto.getEmail())) {
            return new LoginResponse("Invalid email format", false);
        }

        if (userDto.getUsername().length() < 3) {
            return new LoginResponse("Username must be at least 3 characters long", false);
        }

        if (userDto.getPassword().length() < 8) {
            return new LoginResponse("Password must be at least 8 characters long", false);
        }

        if (userRepository.findByUsername(userDto.getUsername().toLowerCase()) != null) {
            return new LoginResponse("Username already in use", false);
        }

        if (userDto.getRole() == null) {
            user = new User(userDto.getId(), userDto.getEmail().toLowerCase(), userDto.getUsername().toLowerCase(), this.passwordEncoder.encode(userDto.getPassword()));
        } else {
            user = new User(userDto.getId(), userDto.getEmail().toLowerCase(), userDto.getUsername().toLowerCase(), this.passwordEncoder.encode(userDto.getPassword()), userDto.getRole());
        }

        userRepository.save(user);
        return new LoginResponse("User " + user.getUsername() + " added", true);
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        User user1 = userRepository.findByUsername(loginDto.getUsername().toLowerCase());
        if (user1 == null) {
            return new LoginResponse("Username does not exists", false);
        }

        String password = loginDto.getPassword();
        String encodedPassword = user1.getPassword();
        boolean isPasswordRight = passwordEncoder.matches(password, encodedPassword);

        if (!isPasswordRight) {
            return new LoginResponse("Error in username and/or password", false);
        }

        Optional<User> user = userRepository.findByUsernameAndPassword(loginDto.getUsername().toLowerCase(), encodedPassword);

        if (user.isEmpty()) {
            return new LoginResponse("Login failed", false);
        }

        return new LoginResponse("Login success", true);
    }

}
