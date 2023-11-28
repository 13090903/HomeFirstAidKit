package com.study.project.services;

import com.study.project.models.Illness;
import com.study.project.models.Symptom;

import java.util.List;

public interface SymptomService {
    Symptom findById(Long id);

    Iterable<Symptom> findAll();

    boolean existsById(Long id);
}
