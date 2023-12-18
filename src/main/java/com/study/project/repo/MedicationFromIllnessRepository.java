package com.study.project.repo;

import com.study.project.models.MedicationFromIllness;
import com.study.project.models.MedicationFromIllnessKey;
import org.springframework.data.repository.CrudRepository;

public interface MedicationFromIllnessRepository extends CrudRepository<MedicationFromIllness, MedicationFromIllnessKey> {
}
