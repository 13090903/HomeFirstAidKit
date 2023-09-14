package com.study.project.controllers;

import com.study.project.models.Illness;
import com.study.project.repo.IllnessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class IllnessesController {
    @Autowired
    private IllnessRepository illnessRepository;

    @GetMapping("/illnesses")
    public String illnesses(Model model) {
        Iterable<Illness> illnesses = illnessRepository.findAll();
        model.addAttribute("illnesses", illnesses);
        return "illnesses";
    }

    @GetMapping("/illnesses/{id}")
    public String illnessDescription(@PathVariable(value = "id") long illnessID, Model model) {
        if (!illnessRepository.existsById(illnessID)) {
            return "redirect:/illnesses";
        }
        Optional<Illness> illness = illnessRepository.findById(illnessID);
        ArrayList<Illness> res = new ArrayList<>();
        illness.ifPresent(res::add);
        model.addAttribute("illness", res);
        return "illness-description";
    }
}
