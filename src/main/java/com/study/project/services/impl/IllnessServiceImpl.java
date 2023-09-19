package com.study.project.services.impl;

import com.study.project.models.Illness;
import com.study.project.repo.IllnessRepository;
import com.study.project.services.IllnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IllnessServiceImpl implements IllnessService {

    @Autowired
    private IllnessRepository illnessRepository;

    @Override
    public Illness findById(Long id) {
        List<Illness> illnesses = findByIdList(id);
        if (illnesses.size() != 0) {
            return illnesses.get(0);
        }
        return null;
    }

    @Override
    public List<Illness> findByIdList(Long id) {
        Optional<Illness> illness = illnessRepository.findById(id);
        List<Illness> res = new ArrayList<>();
        illness.ifPresent(res::add);
        return res;
    }

    @Override
    public Iterable<Illness> findAll() {
        return illnessRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return illnessRepository.existsById(id);
    }
}
