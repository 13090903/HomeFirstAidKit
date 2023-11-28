package com.study.project.util.exceptions;

public class SymptomNotFoundException extends RuntimeException{
    public SymptomNotFoundException() {
        super("Symptom not found");
    }
}
