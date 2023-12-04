package com.study.project.controllers;

import com.study.project.util.exceptions.IllnessNotFoundException;
import com.study.project.util.exceptions.MedicationNotFoundException;
import com.study.project.util.exceptions.SymptomNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(IllnessNotFoundException.class)
    public String illnessNotFoundHandle() {
        return "exception";
    }


//    @ExceptionHandler(MedicationNotFoundException.class)
//    public void conflict2() {
//        // Nothing to do
//    }
//
//    @ExceptionHandler(SymptomNotFoundException.class)
//    public void conflict3() {
//        // Nothing to do
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String conflict() {
//        return "exception";
//    }
}
