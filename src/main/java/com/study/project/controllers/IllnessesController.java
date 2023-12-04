package com.study.project.controllers;

import com.study.project.models.Illness;
import com.study.project.services.IllnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("illness", illnessService.findById(illnessID));
        return "illness-description";
    }

    @GetMapping("/illnesses/add")
    public String illnessAdd(Model model) {
        return "ill-add";
    }

    @PostMapping("/illnesses/add")
    public String addIllness(@RequestParam String name, @RequestParam String description, Model model) {
        Illness illness = illnessService.create(name, description);
        return "redirect:/illnesses";
    }

    @GetMapping("/illnesses/{id}/edit")
    public String illnessEdit(@PathVariable(value = "id") long illnessID, Model model) {
        model.addAttribute("illness", illnessService.findById(illnessID));
        return "illness-edit";
    }

    @PostMapping("/illnesses/{id}/edit")
    public String editIllness(@PathVariable(value = "id") long illnessID, @RequestParam String name, String description, Model model) {
        illnessService.update(illnessID, name, description);
        return "redirect:/illnesses";
    }

    @PostMapping("illnesses/{id}/remove")
    public String removeIllness(@PathVariable(value = "id") long illnessID, Model model) {
        illnessService.deleteById(illnessID);
        return "redirect:/illnesses";
    }
}
