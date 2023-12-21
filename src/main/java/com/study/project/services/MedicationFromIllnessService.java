package com.study.project.services;

import com.study.project.models.Illness;
import com.study.project.models.Medication;
import com.study.project.models.MedicationFromIllness;
import com.study.project.models.MedicationFromIllnessKey;

import java.util.List;

public interface MedicationFromIllnessService {
    Iterable<Medication> findByIllnessId(Long illnessId);

    Iterable<Illness> findByMedicationId(Long medicationId);

    MedicationFromIllness create(Long medicationId, Long illnessId);

    void deleteById(MedicationFromIllnessKey id);
}
