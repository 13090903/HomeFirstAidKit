package com.study.project.controllers;

import com.study.project.models.Symptom;
import com.study.project.services.IllnessSymptomService;
import com.study.project.services.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SymptomsController {

    @Autowired
    private SymptomService symptomService;

    @Autowired
    private IllnessSymptomService illnessSymptomService;

    @GetMapping("/symptoms")
    public String symptoms(Model model) {
        Iterable<Symptom> symptoms = symptomService.findAll();
        model.addAttribute("symptoms", symptoms);
        return "symptoms";
    }

    @GetMapping("/symptoms/{id}")
    public String symptomsDescription(@PathVariable(value = "id") long symptomID, Model model) {
        model.addAttribute("symptom", symptomService.findById(symptomID));
        model.addAttribute("illnesses", illnessSymptomService.findBySymptomId(symptomID));
        return "symptoms-description";
    }
}
