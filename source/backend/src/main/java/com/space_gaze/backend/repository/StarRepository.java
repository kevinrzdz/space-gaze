package com.space_gaze.backend.repository;

import com.space_gaze.backend.entity.Star;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StarRepository extends JpaRepository<Star, Integer> {
    @Query("SELECT s FROM Star s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Star> findByName(String name, Pageable pageable);
}
