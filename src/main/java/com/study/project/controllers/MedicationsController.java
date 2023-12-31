package com.study.project.controllers;

import com.study.project.models.*;
import com.study.project.services.IllnessService;
import com.study.project.services.ManufacturerService;
import com.study.project.services.MedicationFromIllnessService;
import com.study.project.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

@Controller
public class MedicationsController {

    @Autowired
    private MedicationService medicationService;
    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private MedicationFromIllnessService medicationFromIllnessService;

    @Autowired
    private IllnessService illnessService;

    @GetMapping("/medications")
    public String medications(Model model) {
        Iterable<Medication> medications = medicationService.findAll();
        model.addAttribute("medications", medications);
        return "medications";
    }

    @GetMapping("/medications/add")
    public String medicationAdd(Model model) {
        model.addAttribute("illnesses", illnessService.findAll());
        return "med-add";
    }

    @PostMapping("/medications/add")
    public String addMedication(@RequestParam String name, @RequestParam LocalDate expiration_date, @RequestParam Long price, @RequestParam Long amount, @RequestParam String manufacturer_name, @RequestParam String manufacturer_country, @RequestParam(value = "illnessesBox")Long[] illnesses, Model model) {
        Manufacturer manufacturer = manufacturerService.existByParamsOrElseCreate(manufacturer_name, manufacturer_country);
        Medication medication = medicationService.create(name, price, manufacturer, expiration_date, amount);
        for (long illnessId : illnesses) {
            medicationFromIllnessService.create(medication.getId(), illnessId);
        }
        return "redirect:/medications";
    }

    @GetMapping("/medications/{id}/edit")
    public String medicationEdit(@PathVariable(value = "id") long medicationID, Model model) {
        model.addAttribute("medication", medicationService.findById(medicationID));
        Iterable<Illness> checkedIllnesses = medicationFromIllnessService.findByMedicationId(medicationID);
        Set<Long> checked = new HashSet<>();
        for (Illness illness : checkedIllnesses) {
            checked.add(illness.getId());
        }
        model.addAttribute("checked", checked);
        model.addAttribute("illnesses", illnessService.findAll());
        return "medication-edit";
    }

    @PostMapping("/medications/{id}/edit")
    public String editMedication(@PathVariable(value = "id") long medicationID, @RequestParam String name, @RequestParam LocalDate expiration_date, @RequestParam Long price, @RequestParam Long amount, @RequestParam String manufacturer_name, @RequestParam String manufacturer_country, @RequestParam(value = "illnessesBox")Long[] illnesses, Model model) {
        Long manufacturerId = medicationService.findManufacturerByMedicationId(medicationID).getId();
        Manufacturer manufacturer = manufacturerService.findById(manufacturerId);
        manufacturer.removeMedication(medicationService.findById(medicationID));
        manufacturerService.update(manufacturerId, manufacturer_name, manufacturer_country);
        Manufacturer newManufacturer = manufacturerService.existByParams(manufacturer_name, manufacturer_country);
        newManufacturer.addMedication(medicationService.findById(medicationID));
        medicationService.update(medicationID, name, expiration_date, price, amount, newManufacturer);
        manufacturerService.deleteUselessById(manufacturerId);
        for (Illness illness : medicationFromIllnessService.findByMedicationId(medicationID)) {
            MedicationFromIllnessKey key = new MedicationFromIllnessKey();
            key.setMedicationId(medicationID);
            key.setIllnessId(illness.getId());
            medicationFromIllnessService.deleteById(key);
        }
        for (long illnessId : illnesses) {
            medicationFromIllnessService.create(medicationID, illnessId);
        }
        return "redirect:/medications";
    }

    @PostMapping("/medications/{id}/remove")
    public String removeMedication(@PathVariable(value = "id") long medicationID, Model model) {
        medicationService.deleteById(medicationID);
        return "redirect:/medications";
    }

}
