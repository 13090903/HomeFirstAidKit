package com.study.project.services;

import com.study.project.models.Illness;

public interface IllnessService {
    Illness findById(Long id);

    Iterable<Illness> findAll();

    boolean existsById(Long id);

    Illness create(String name, String description);

    void update(Long id, String name, String description);

    void deleteById(Long id);

}
