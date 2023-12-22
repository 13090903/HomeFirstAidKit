package com.study.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "illness")
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "illness")
    Set<MedicationFromIllness> medicationFromIllnesses = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "illness")
    Set<IllnessSymptom> illnessSymptoms  = new HashSet<>();

    public Illness() {
    }

    public Illness(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
