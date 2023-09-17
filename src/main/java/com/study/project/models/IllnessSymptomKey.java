package com.study.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class IllnessSymptomKey {

    @Column(name = "illness_id")
    private Long illnessId;

    @Column(name = "symptom_id")
    private Long symptomId;

    public IllnessSymptomKey(Long illnessId, Long symptomId) {
        this.illnessId = illnessId;
        this.symptomId = symptomId;
    }

    public Long getIllnessId() {
        return illnessId;
    }

    public void setIllnessId(Long illnessId) {
        this.illnessId = illnessId;
    }

    public Long getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Long symptomId) {
        this.symptomId = symptomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IllnessSymptomKey that = (IllnessSymptomKey) o;
        return illnessId.equals(that.illnessId) && symptomId.equals(that.symptomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(illnessId, symptomId);
    }
}
