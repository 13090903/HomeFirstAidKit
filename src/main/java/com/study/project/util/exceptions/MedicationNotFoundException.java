package com.study.project.util.exceptions;

public class MedicationNotFoundException extends RuntimeException{
    public MedicationNotFoundException() {
        super("Medication not found");
    }
}
