package com.study.project.services.impl;

import com.study.project.models.Illness;
import com.study.project.models.Manufacturer;
import com.study.project.models.Medication;
import com.study.project.repo.ManufacturerRepository;
import com.study.project.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer findById(Long id) {
        List<Manufacturer> manufacturers = findByIdList(id);
        if (manufacturers.size() != 0) {
            return manufacturers.get(0);
        }
        return null;
    }

    @Override
    public List<Manufacturer> findByIdList(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        List<Manufacturer> res = new ArrayList<>();
        manufacturer.ifPresent(res::add);
        return res;
    }

    @Override
    public Iterable<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer existByParamsOrElseCreate(String companyName, String country) {
        Iterable<Manufacturer> manufacturers = manufacturerRepository.findAll();
        Manufacturer manufacturer = null;
        for (Manufacturer m : manufacturers) {
            if (m.getCompanyName().equals(companyName) && m.getCountry().equals(country)) {
                manufacturer = m;
                break;
            }
        }
        if (manufacturer == null) {
            manufacturer = create(companyName, country);
        }
        return manufacturer;
    }

    @Override
    public Manufacturer existByParams(String companyName, String country) {
        Iterable<Manufacturer> manufacturers = manufacturerRepository.findAll();
        for (Manufacturer m : manufacturers) {
            if (m.getCompanyName().equals(companyName) && m.getCountry().equals(country)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return manufacturerRepository.existsById(id);
    }

    @Override
    public Manufacturer create(String companyName, String country) {
        Manufacturer manufacturer = new Manufacturer(country, companyName);
        manufacturerRepository.save(manufacturer);
        return manufacturer;
    }

    @Override
    public void update(Long id, String companyName, String country) {
        Manufacturer manufacturer = existByParams(companyName, country);
        if (manufacturer == null) {
            create(companyName, country);
        }
    }

    @Override
    public void deleteUseless() {
        for (Manufacturer m : findAll()) {
            if (m.getMedications().size() == 0) {
                deleteById(m.getId());
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        Manufacturer manufacturer = findById(id);
        manufacturerRepository.delete(manufacturer);
    }
}
