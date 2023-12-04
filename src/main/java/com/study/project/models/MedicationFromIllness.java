package com.study.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
