package com.study.project.controllers;

import com.study.project.repo.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ManufacturerController {

    @Autowired
    ManufacturerRepository manufacturerRepository;


}
