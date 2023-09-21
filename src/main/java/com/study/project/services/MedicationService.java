package com.study.project.services;

import com.study.project.models.Manufacturer;
import com.study.project.models.Medication;
import com.study.project.models.Symptom;

import java.time.LocalDate;
import java.util.List;

public interface MedicationService {
    Medication findById(Long id);

    List<Medication> findByIdList(Long id);

    Iterable<Medication> findAll();

    boolean existsById(Long id);

    Medication create(String name, Long price, Manufacturer manufacturer, LocalDate expirationDate, Long amount);

    void update(Long id, String name, LocalDate expiration_date, Long price, Long amount, Manufacturer manufacturer);

    void deleteById(Long id);

    Manufacturer findManufacturerByMedicationId(Long id);
}
