package com.study.project.repo;

import com.study.project.models.Illness;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IllnessRepository extends CrudRepository<Illness, Long> {
}
