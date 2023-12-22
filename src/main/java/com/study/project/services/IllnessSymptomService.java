package com.study.project.services;

import com.study.project.models.*;

import java.util.List;

public interface IllnessSymptomService {
    Iterable<Symptom> findByIllnessId(Long illnessId);

    Iterable<Illness> findBySymptomId(Long symptomId);

    IllnessSymptom create(Long illnessId, Long symptomId);

    void deleteById(IllnessSymptomKey id);
}
