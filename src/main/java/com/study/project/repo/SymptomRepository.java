package com.study.project.repo;

import com.study.project.models.Symptom;
import org.springframework.data.repository.CrudRepository;

public interface SymptomRepository extends CrudRepository<Symptom, Long> {
}
