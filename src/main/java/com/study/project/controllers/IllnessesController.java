package com.study.project.controllers;

import com.study.project.models.*;
import com.study.project.services.IllnessService;
import com.study.project.services.IllnessSymptomService;
import com.study.project.services.MedicationFromIllnessService;
import com.study.project.services.SymptomService;
import com.study.project.services.impl.IllnessSymptomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class IllnessesController {
    @Autowired
    private IllnessService illnessService;

    @Autowired
    private SymptomService symptomService;

    @Autowired
    private MedicationFromIllnessService medicationFromIllnessService;

    @Autowired
    private IllnessSymptomService illnessSymptomService;

    @GetMapping("/illnesses")
    public String illnesses(Model model) {
        Iterable<Illness> illnesses = illnessService.findAll();
        model.addAttribute("illnesses", illnesses);
        return "illnesses";
    }

    @GetMapping("/illnesses/{id}")
    public String illnessDescription(@PathVariable(value = "id") long illnessID, Model model) {
        model.addAttribute("illness", illnessService.findById(illnessID));
        model.addAttribute("meds", medicationFromIllnessService.findByIllnessId(illnessID));
        return "illness-description";
    }

    @GetMapping("/illnesses/add")
    public String illnessAdd(Model model) {
        model.addAttribute("symptoms", symptomService.findAll());
        return "ill-add";
    }

    @PostMapping("/illnesses/add")
    public String addIllness(@RequestParam String name, @RequestParam String description, @RequestParam(value = "symptomsBox")Long[] symptoms, Model model) {
        Illness illness = illnessService.create(name, description);
        for (long symptomId : symptoms) {
            illnessSymptomService.create(illness.getId(), symptomId);
        }
        return "redirect:/illnesses";
    }

    @GetMapping("/illnesses/{id}/edit")
    public String illnessEdit(@PathVariable(value = "id") long illnessID, Model model) {
        model.addAttribute("illness", illnessService.findById(illnessID));
        Iterable<Symptom> checkedSymptoms = illnessSymptomService.findByIllnessId(illnessID);
        Set<Long> checked = new HashSet<>();
        for (Symptom symptom : checkedSymptoms) {
            checked.add(symptom.getId());
        }
        model.addAttribute("checked", checked);
        model.addAttribute("symptoms", symptomService.findAll());
        return "illness-edit";
    }

    @PostMapping("/illnesses/{id}/edit")
    public String editIllness(@PathVariable(value = "id") long illnessID, @RequestParam String name, String description, @RequestParam(value = "symptomsBox")Long[] symptoms, Model model) {
        illnessService.update(illnessID, name, description);
        for (Symptom symptom : illnessSymptomService.findByIllnessId(illnessID)) {
            IllnessSymptomKey key = new IllnessSymptomKey();
            key.setIllnessId(illnessID);
            key.setSymptomId(symptom.getId());
            illnessSymptomService.deleteById(key);
        }
        for (long symptomId : symptoms) {
            illnessSymptomService.create(illnessID, symptomId);
        }
        return "redirect:/illnesses";
    }

    @PostMapping("illnesses/{id}/remove")
    public String removeIllness(@PathVariable(value = "id") long illnessID, Model model) {
        illnessService.deleteById(illnessID);
        return "redirect:/illnesses";
    }
}
