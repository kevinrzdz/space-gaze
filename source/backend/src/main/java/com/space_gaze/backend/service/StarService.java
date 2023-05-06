package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.Star;
import com.space_gaze.backend.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarService implements CrudServiceInterface<Star, Integer> {

    @Autowired
    private StarRepository starRepository;

    @Override
    public List<Star> findAll() {
        return starRepository.findAll();
    }

    public List<Star> search(String name) {
        List<Star> stars = starRepository.findAll();

        if (name == null || name.trim().isEmpty()) {
            return stars;
        } else {
            return stars.stream()
                    .filter(star -> star.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Page<Star> findAll(Pageable pageable) {
        return starRepository.findAll(pageable);
    }

    @Override
    public Star findById(Integer id) {
        return starRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not found star with id: " + id));
    }

    @Override
    public Star save(Star star) {
        return starRepository.save(star);
    }

    @Override
    public void deleteById(Integer id) {
        starRepository.deleteById(id);
    }
}
