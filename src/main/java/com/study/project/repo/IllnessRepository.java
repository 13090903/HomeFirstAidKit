package com.study.project.repo;

import com.study.project.models.Illness;
import org.springframework.data.repository.CrudRepository;

public interface IllnessRepository extends CrudRepository<Illness, Long> {
}
