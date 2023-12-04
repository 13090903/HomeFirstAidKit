package com.study.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MedicationFromIllnessKey implements Serializable {

    @Column(name = "medication_id")
    private Long medicationId;

    @Column(name = "illness_id")
    private Long illnessId;

    public MedicationFromIllnessKey(Long medicationId, Long illnessId) {
        this.medicationId = medicationId;
        this.illnessId = illnessId;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public Long getIllnessId() {
        return illnessId;
    }

    public void setIllnessId(Long illnessId) {
        this.illnessId = illnessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationFromIllnessKey that = (MedicationFromIllnessKey) o;
        return medicationId.equals(that.medicationId) && illnessId.equals(that.illnessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationId, illnessId);
    }
}
