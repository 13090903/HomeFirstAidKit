package com.study.project.services.impl;

import com.study.project.models.*;
import com.study.project.repo.IllnessRepository;
import com.study.project.repo.IllnessSymptomRepository;
import com.study.project.repo.SymptomRepository;
import com.study.project.services.IllnessSymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class IllnessSymptomServiceImpl implements IllnessSymptomService {

    @Autowired
    private IllnessRepository illnessRepository;

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private IllnessSymptomRepository illnessSymptomRepository;


    @Override
    public Iterable<Symptom> findByIllnessId(Long illnessId) {
        Illness illness = illnessRepository.findById(illnessId).orElse(null);
        Set<IllnessSymptom> illnessSymptoms = illness != null ? illness.getIllnessSymptoms() : new HashSet<>();
        List<Symptom> symptoms = new ArrayList<>();
        for (IllnessSymptom illnessSymptom : illnessSymptoms) {
            symptoms.add(illnessSymptom.getSymptom());
        }
        return symptoms;
    }

    @Override
    public Iterable<Illness> findBySymptomId(Long symptomId) {
        Symptom symptom = symptomRepository.findById(symptomId).orElse(null);
        Set<IllnessSymptom> illnessSymptoms = symptom != null ? symptom.getIllnessSymptoms() : new HashSet<>();
        List<Illness> illnesses = new ArrayList<>();
        for (IllnessSymptom illnessSymptom : illnessSymptoms) {
            illnesses.add(illnessSymptom.getIllness());
        }
        return illnesses;
    }

    @Override
    public IllnessSymptom create(Long illnessId, Long symptomId) {
        Illness illness = illnessRepository.findById(illnessId).orElseThrow();
        Symptom symptom = symptomRepository.findById(symptomId).orElseThrow();
        IllnessSymptom is = new IllnessSymptom(illness, symptom);
        is.setId(new IllnessSymptomKey());
        is.getId().setIllnessId(illnessId);
        is.getId().setSymptomId(symptomId);
        illness.getIllnessSymptoms().add(is);
        symptom.getIllnessSymptoms().add(is);
        illnessSymptomRepository.save(is);
        return is;
    }

    @Override
    public void deleteById(IllnessSymptomKey id) {
        IllnessSymptom is = illnessSymptomRepository.findById(id).orElseThrow();
        illnessSymptomRepository.delete(is);
    }
}
