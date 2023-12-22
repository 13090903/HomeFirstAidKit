package com.study.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "symptom")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "symptom")
    Set<IllnessSymptom> illnessSymptoms  = new HashSet<>();

    public Symptom() {
    }

    public Symptom(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
