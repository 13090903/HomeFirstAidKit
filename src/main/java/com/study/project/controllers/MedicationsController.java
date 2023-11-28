package com.study.project.controllers;

import com.study.project.models.Illness;
import com.study.project.models.Manufacturer;
import com.study.project.models.Medication;
import com.study.project.repo.ManufacturerRepository;
import com.study.project.repo.MedicationRepository;
import com.study.project.services.ManufacturerService;
import com.study.project.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class MedicationsController {

    @Autowired
    private MedicationService medicationService;
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/medications")
    public String medications(Model model) {
        Iterable<Medication> medications = medicationService.findAll();
        model.addAttribute("medications", medications);
        return "medications";
    }

    @GetMapping("/medications/add")
    public String medicationAdd(Model model) {
        return "med-add";
    }

    @PostMapping("/medications/add")
    public String addMedication(@RequestParam String name, @RequestParam LocalDate expiration_date, @RequestParam Long price, @RequestParam Long amount, @RequestParam String manufacturer_name, @RequestParam String manufacturer_country, Model model) {
        Manufacturer manufacturer = manufacturerService.existByParamsOrElseCreate(manufacturer_name, manufacturer_country);
        Medication medication = medicationService.create(name, price, manufacturer, expiration_date, amount);
        return "redirect:/medications";
    }

    @GetMapping("/medications/{id}/edit")
    public String medicationEdit(@PathVariable(value = "id") long medicationID, Model model) {
        model.addAttribute("medication", medicationService.findById(medicationID));
        return "medication-edit";
    }

    @PostMapping("/medications/{id}/edit")
    public String editMedication(@PathVariable(value = "id") long medicationID, @RequestParam String name, @RequestParam LocalDate expiration_date, @RequestParam Long price, @RequestParam Long amount, @RequestParam String manufacturer_name, @RequestParam String manufacturer_country, Model model) {
        Long manufacturerId = medicationService.findManufacturerByMedicationId(medicationID).getId();
        Manufacturer manufacturer = manufacturerService.findById(manufacturerId);
        manufacturer.removeMedication(medicationService.findById(medicationID));
        manufacturerService.update(manufacturerId, manufacturer_name, manufacturer_country);
        Manufacturer newManufacturer = manufacturerService.existByParams(manufacturer_name, manufacturer_country);
        newManufacturer.addMedication(medicationService.findById(medicationID));
        medicationService.update(medicationID, name, expiration_date, price, amount, newManufacturer);
        manufacturerService.deleteUselessById(manufacturerId);
        return "redirect:/medications";
    }

    @PostMapping("/medications/{id}/remove")
    public String removeMedication(@PathVariable(value = "id") long medicationID, Model model) {
        medicationService.deleteById(medicationID);
        return "redirect:/medications";
    }

}
