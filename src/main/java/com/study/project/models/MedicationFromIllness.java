package com.study.project.models;

import jakarta.persistence.*;

@Entity
public class MedicationFromIllness {

    @EmbeddedId
    MedicationFromIllnessKey id;

    @ManyToOne
    @MapsId("medicationId")
    @JoinColumn(name = "medication_id")
    Medication medication;

    @ManyToOne
    @MapsId("illnessId")
    @JoinColumn(name = "illness_id")
    Illness illness;

    public MedicationFromIllness(Medication medication, Illness illness) {
        this.medication = medication;
        this.illness = illness;
    }

    public MedicationFromIllnessKey getId() {
        return id;
    }

    public void setId(MedicationFromIllnessKey id) {
        this.id = id;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Illness getIllness() {
        return illness;
    }

    public void setIllness(Illness illness) {
        this.illness = illness;
    }
}
