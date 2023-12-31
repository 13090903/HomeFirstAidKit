package com.study.project.services.impl;

import com.study.project.models.Illness;
import com.study.project.repo.IllnessRepository;
import com.study.project.services.IllnessService;
import com.study.project.util.exceptions.IllnessNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IllnessServiceImpl implements IllnessService {

    @Autowired
    private IllnessRepository illnessRepository;

    @Override
    public Illness findById(Long id) {
        Optional<Illness> illness = illnessRepository.findById(id);
        return illness.orElseThrow(IllnessNotFoundException::new);
    }

    @Override
    public Iterable<Illness> findAll() {
        return illnessRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return illnessRepository.existsById(id);
    }

    @Override
    public Illness create(String name, String description) {
        Illness illness = new Illness(name, description);
        illnessRepository.save(illness);
        return illness;
    }

    @Override
    public void update(Long id, String name, String description) {
        Illness illness = illnessRepository.findById(id).orElseThrow();
        illness.setName(name);
        illness.setDescription(description);
        illnessRepository.save(illness);
    }

    @Override
    public void deleteById(Long id) {
        Illness illness = illnessRepository.findById(id).orElseThrow();
        illnessRepository.delete(illness);
    }
}
