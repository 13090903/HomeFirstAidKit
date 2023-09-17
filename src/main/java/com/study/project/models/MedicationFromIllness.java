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
}
