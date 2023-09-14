package com.study.project.controllers;

import com.study.project.models.Illness;
import com.study.project.models.Symptom;
import com.study.project.repo.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SymptomsController {

    @Autowired
    private SymptomRepository symptomRepository;

    @GetMapping("/symptoms")
    public String symptoms(Model model) {
        Iterable<Symptom> symptoms = symptomRepository.findAll();
        model.addAttribute("symptoms", symptoms);
        return "symptoms";
    }

    @GetMapping("/symptoms/{id}")
    public String symptomsDescription(@PathVariable(value = "id") long symptomID, Model model) {
        if (!symptomRepository.existsById(symptomID)) {
            return "redirect:/symptoms";
        }
        Optional<Symptom> symptom = symptomRepository.findById(symptomID);
        ArrayList<Symptom> res = new ArrayList<>();
        symptom.ifPresent(res::add);
        model.addAttribute("symptom", res);
        return "illness-description";
    }
}
