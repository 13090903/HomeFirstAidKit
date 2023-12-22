package com.study.project.repo;

import com.study.project.models.IllnessSymptom;
import com.study.project.models.IllnessSymptomKey;
import org.springframework.data.repository.CrudRepository;

public interface IllnessSymptomRepository extends CrudRepository<IllnessSymptom, IllnessSymptomKey> {
}
