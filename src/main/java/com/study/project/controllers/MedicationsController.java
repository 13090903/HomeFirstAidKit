package com.study.project.controllers;

import com.study.project.models.Illness;
import com.study.project.models.Manufacturer;
import com.study.project.models.Medication;
import com.study.project.repo.ManufacturerRepository;
import com.study.project.repo.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MedicationsController {

    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;


    @GetMapping("/medications")
    public String medications(Model model) {
        Iterable<Medication> medications = medicationRepository.findAll();
        model.addAttribute("medications", medications);
        return "medications";
    }

    @GetMapping("/medications/add")
    public String medicationAdd(Model model) {
        return "med-add";
    }

    @PostMapping("/medications/add")
    public String addMedication(@RequestParam String name, @RequestParam LocalDate expiration_date, @RequestParam Long price, @RequestParam Long amount, @RequestParam String manufacturer_name, @RequestParam String manufacturer_country, Model model) {
        //TODO: add services and make functions there
        Iterable<Manufacturer> manufacturers = manufacturerRepository.findAll();
        Manufacturer manufacturer = null;
        for (Manufacturer m : manufacturers) {
            if (m.getCompanyName().equals(manufacturer_name) && m.getCountry().equals(manufacturer_country)) {
                manufacturer = m;
                break;
            }
        }
        if (manufacturer == null) {
            manufacturer = new Manufacturer(manufacturer_country, manufacturer_name);
        }
        Medication medication = new Medication(name, price, manufacturer, expiration_date, amount);
        manufacturer.addMedication(medication);
        manufacturerRepository.save(manufacturer);
        medicationRepository.save(medication);
        return "redirect:/medications";
    }

    @GetMapping("/medications/{id}/edit")
    public String medicationEdit(@PathVariable(value = "id") long medicationID, Model model) {
        if (!medicationRepository.existsById(medicationID)) {
            return "redirect:/medications";
        }
        Optional<Medication> medication = medicationRepository.findById(medicationID);
        ArrayList<Medication> res = new ArrayList<>();
        medication.ifPresent(res::add);
        model.addAttribute("medication", res);
        return "medication-edit";
    }

    @PostMapping("/medications/{id}/edit")
    public String editMedication(@PathVariable(value = "id") long medicationID, @RequestParam String name, @RequestParam LocalDate expiration_date, @RequestParam Long price, @RequestParam Long amount,  Model model) {
        Medication medication = medicationRepository.findById(medicationID).orElseThrow();
        medication.setName(name);
        medication.setPrice(price);
        medication.setAmount(amount);
        medication.setExpirationDate(expiration_date);
        medicationRepository.save(medication);
        return "redirect:/medications";
    }

    @GetMapping("/medications/{id}/remove")
    public String medicationRemove(@PathVariable(value = "id") long medicationID, Model model) {
        if (!medicationRepository.existsById(medicationID)) {
            return "redirect:/medications";
        }
        Optional<Medication> medication = medicationRepository.findById(medicationID);
        ArrayList<Medication> res = new ArrayList<>();
        medication.ifPresent(res::add);
        model.addAttribute("medication", res);
        return "medication-remove";
    }

    @PostMapping("/medications/{id}/remove")
    public String removeMedication(@PathVariable(value = "id") long medicationID, Model model) {
        Medication medication = medicationRepository.findById(medicationID).orElseThrow();
        medicationRepository.delete(medication);
        return "redirect:/medications";
    }

}
