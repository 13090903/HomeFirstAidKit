package com.study.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
