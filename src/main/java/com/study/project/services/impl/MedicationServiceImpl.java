package com.study.project.services.impl;

import com.study.project.models.Illness;
import com.study.project.models.Manufacturer;
import com.study.project.models.Medication;
import com.study.project.models.Symptom;
import com.study.project.repo.MedicationRepository;
import com.study.project.services.MedicationService;
import com.study.project.util.exceptions.IllnessNotFoundException;
import com.study.project.util.exceptions.MedicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Medication findById(Long id) {
        Optional<Medication> medication = medicationRepository.findById(id);
        return medication.orElseThrow(MedicationNotFoundException::new);
    }

    @Override
    public Iterable<Medication> findAll() {
        return medicationRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return medicationRepository.existsById(id);
    }

    @Override
    public Medication create(String name, Long price, Manufacturer manufacturer, LocalDate expirationDate, Long amount) {
        Medication medication = new Medication(name, price, manufacturer, expirationDate, amount);
        manufacturer.addMedication(medication);
        medicationRepository.save(medication);
        return medication;
    }

    @Override
    public void update(Long id, String name, LocalDate expiration_date, Long price, Long amount, Manufacturer manufacturer) {
        Medication medication = medicationRepository.findById(id).orElseThrow();
        medication.setManufacturer(manufacturer);
        medication.setName(name);
        medication.setPrice(price);
        medication.setAmount(amount);
        medication.setExpirationDate(expiration_date);
        medicationRepository.save(medication);
    }

    @Override
    public void deleteById(Long id) {
        Medication medication = medicationRepository.findById(id).orElseThrow();
        Manufacturer manufacturer = medication.getManufacturer();
        manufacturer.getMedications().remove(medication);
        medicationRepository.delete(medication);
    }

    @Override
    public Manufacturer findManufacturerByMedicationId(Long id) {
        Medication medication = findById(id);
        if (medication != null) {
            return medication.getManufacturer();
        }
        return null;
    }
}
