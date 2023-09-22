package com.study.project.services;

import com.study.project.models.Manufacturer;
import com.study.project.models.Symptom;

import java.util.List;

public interface ManufacturerService {
    Manufacturer findById(Long id);

    List<Manufacturer> findByIdList(Long id);

    Iterable<Manufacturer> findAll();

    Manufacturer existByParamsOrElseCreate(String companyName, String country);

    Manufacturer existByParams(String companyName, String country);

    boolean existsById(Long id);

    Manufacturer create(String companyName, String country);

    void update(Long id, String companyName, String country);

    void deleteById(Long id);

    void deleteUselessById(Long id);

}
