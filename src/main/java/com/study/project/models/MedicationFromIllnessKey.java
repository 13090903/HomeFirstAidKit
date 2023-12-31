package com.study.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class MedicationFromIllnessKey implements Serializable {

    @Column(name = "medication_id")
    private Long medicationId;

    @Column(name = "illness_id")
    private Long illnessId;

    public MedicationFromIllnessKey() {
    }
}
