package com.study.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class IllnessSymptomKey {

    @Column(name = "illness_id")
    private Long illnessId;

    @Column(name = "symptom_id")
    private Long symptomId;

    public IllnessSymptomKey() {
    }
}
