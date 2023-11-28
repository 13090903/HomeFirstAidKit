package com.study.project.services.impl;

import com.study.project.models.Illness;
import com.study.project.models.Symptom;
import com.study.project.repo.SymptomRepository;
import com.study.project.services.SymptomService;
import com.study.project.util.exceptions.IllnessNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    @Override
    public Symptom findById(Long id) {
        Optional<Symptom> symptom = symptomRepository.findById(id);
        return symptom.orElseThrow(IllnessNotFoundException::new);
    }

    @Override
    public Iterable<Symptom> findAll() {
        return symptomRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return symptomRepository.existsById(id);
    }
}
