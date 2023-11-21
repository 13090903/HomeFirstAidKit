package com.study.project.services;

import com.study.project.models.Illness;
import com.study.project.models.Manufacturer;
import com.study.project.models.Medication;

import java.time.LocalDate;
import java.util.List;

public interface IllnessService {
    Illness findById(Long id);

    List<Illness> findByIdList(Long id);

    Iterable<Illness> findAll();

    boolean existsById(Long id);

    Illness create(String name, String description);

    void update(Long id, String name, String description);

    void deleteById(Long id);

}
