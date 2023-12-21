package com.study.project.services.impl;

import com.study.project.models.*;
import com.study.project.repo.IllnessRepository;
import com.study.project.repo.MedicationFromIllnessRepository;
import com.study.project.repo.MedicationRepository;
import com.study.project.services.MedicationFromIllnessService;
import com.study.project.util.exceptions.MedicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    private MedicationFromIllnessRepository medicationFromIllnessRepository;

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

    @Override
    public MedicationFromIllness create(Long medicationId, Long illnessId) {
        Medication medication = medicationRepository.findById(medicationId).orElseThrow();
        Illness illness = illnessRepository.findById(illnessId).orElseThrow();
        MedicationFromIllness mi = new MedicationFromIllness(medication, illness);
        mi.setId(new MedicationFromIllnessKey());
        mi.getId().setMedicationId(medicationId);
        mi.getId().setIllnessId(illnessId);
        medication.getMedicationFromIllnesses().add(mi);
        illness.getMedicationFromIllnesses().add(mi);
        medicationFromIllnessRepository.save(mi);
        return mi;
    }

    @Override
    public void deleteById(MedicationFromIllnessKey id) {
        MedicationFromIllness mi = medicationFromIllnessRepository.findById(id).orElseThrow();
        medicationFromIllnessRepository.delete(mi);
    }
}
