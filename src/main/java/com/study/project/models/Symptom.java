package com.study.project.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "symptom")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "symptom")
    Set<IllnessSymptom> illnessSymptoms;

    public Symptom() {
    }

    public Symptom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<IllnessSymptom> getIllnessSymptoms() {
        return illnessSymptoms;
    }

    public void setIllnessSymptoms(Set<IllnessSymptom> illnessSymptoms) {
        this.illnessSymptoms = illnessSymptoms;
    }
}
