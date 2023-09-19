package com.study.project.services;

import com.study.project.models.Illness;
import com.study.project.models.Medication;

import java.util.List;

public interface MedicationFromIllnessService {
    Iterable<Medication> findByIllnessId(Long illnessId);

    Iterable<Illness> findByMedicationId(Long medicationId);
}
