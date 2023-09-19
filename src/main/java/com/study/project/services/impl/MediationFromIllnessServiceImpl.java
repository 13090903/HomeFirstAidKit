package com.study.project.services.impl;

import com.study.project.models.*;
import com.study.project.repo.IllnessRepository;
import com.study.project.repo.MedicationRepository;
import com.study.project.services.MedicationFromIllnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MediationFromIllnessServiceImpl implements MedicationFromIllnessService {

    @Autowired
    private IllnessRepository illnessRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Iterable<Medication> findByIllnessId(Long illnessId) {
        Illness illness = illnessRepository.findById(illnessId).orElse(null);
        Set<MedicationFromIllness> medicationFromIllnesses = illness != null ? illness.getMedicationFromIllnesses() : new HashSet<>();
        List<Medication> medications = new ArrayList<>();
        for (MedicationFromIllness medicationFromIllness : medicationFromIllnesses) {
            medications.add(medicationFromIllness.getMedication());
        }
        return medications;
    }

    @Override
    public Iterable<Illness> findByMedicationId(Long medicationId) {
        Medication medication = medicationRepository.findById(medicationId).orElse(null);
        Set<MedicationFromIllness> medicationFromIllnesses = medication != null ? medication.getMedicationFromIllnesses() : new HashSet<>();
        List<Illness> illnesses = new ArrayList<>();
        for (MedicationFromIllness medicationFromIllness : medicationFromIllnesses) {
            illnesses.add(medicationFromIllness.getIllness());
        }
        return illnesses;
    }
}
