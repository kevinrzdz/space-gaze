package com.space_gaze.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudServiceInterface<T, E> {

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    T findById(E id);

    T save(T entity);

    void deleteById(E id);
}
