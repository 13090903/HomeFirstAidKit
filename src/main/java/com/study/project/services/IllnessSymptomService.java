package com.study.project.services;

import com.study.project.models.Illness;
import com.study.project.models.Symptom;

import java.util.List;

public interface IllnessSymptomService {
    Iterable<Symptom> findByIllnessId(Long illnessId);

    Iterable<Illness> findBySymptomId(Long symptomId);
}
