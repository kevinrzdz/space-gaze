package com.space_gaze.backend.service;

import com.space_gaze.backend.entity.AstronomicalEvent;
import com.space_gaze.backend.repository.AstronomicalEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AstronomicalEventService implements CrudServiceInterface<AstronomicalEvent, Integer> {

    @Autowired
    private AstronomicalEventRepository astronomicalEventRepository;

    @Override
    public List<AstronomicalEvent> findAll() {
        return astronomicalEventRepository.findAll();
    }

    @Override
    public Page<AstronomicalEvent> findAll(Pageable pageable) {
        return astronomicalEventRepository.findAll(pageable);
    }

    @Override
    public AstronomicalEvent findById(Integer id) {
        return astronomicalEventRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not found event with id: " + id));
    }

    @Override
    public AstronomicalEvent save(AstronomicalEvent entity) {
        return astronomicalEventRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        astronomicalEventRepository.deleteById(id);
    }
}