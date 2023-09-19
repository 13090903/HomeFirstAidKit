package com.study.project.controllers;

import com.study.project.models.Illness;
import com.study.project.repo.IllnessRepository;
import com.study.project.services.IllnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class IllnessesController {
    @Autowired
    private IllnessService illnessService;

    @GetMapping("/illnesses")
    public String illnesses(Model model) {
        Iterable<Illness> illnesses = illnessService.findAll();
        model.addAttribute("illnesses", illnesses);
        return "illnesses";
    }

    @GetMapping("/illnesses/{id}")
    public String illnessDescription(@PathVariable(value = "id") long illnessID, Model model) {
        if (!illnessService.existsById(illnessID)) {
            return "redirect:/illnesses";
        }
        model.addAttribute("illness", illnessService.findByIdList(illnessID));
        return "illness-description";
    }
}
