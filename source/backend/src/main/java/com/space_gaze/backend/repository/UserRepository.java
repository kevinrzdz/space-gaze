package com.space_gaze.backend.repository;

import com.space_gaze.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
