package com.study.project.models;

import jakarta.persistence.*;

@Entity
public class IllnessSymptom {

    @EmbeddedId
    private IllnessSymptomKey id;

    @ManyToOne
    @MapsId("illnessId")
    @JoinColumn(name = "illness_id")
    Illness illness;

    @ManyToOne
    @MapsId("symptomId")
    @JoinColumn(name = "symptom_id")
    Symptom symptom;
}
