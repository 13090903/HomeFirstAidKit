package com.study.project.services;

import com.study.project.models.Illness;

import java.util.List;

public interface IllnessService {
    Illness findById(Long id);

    List<Illness> findByIdList(Long id);

    Iterable<Illness> findAll();

    boolean existsById(Long id);

}
