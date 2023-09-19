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

    public IllnessSymptom(Illness illness, Symptom symptom) {
        this.illness = illness;
        this.symptom = symptom;
    }

    public IllnessSymptomKey getId() {
        return id;
    }

    public void setId(IllnessSymptomKey id) {
        this.id = id;
    }

    public Illness getIllness() {
        return illness;
    }

    public void setIllness(Illness illness) {
        this.illness = illness;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }
}
