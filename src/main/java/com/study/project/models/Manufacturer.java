package com.study.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    @OneToMany(mappedBy="manufacturer")
    private Set<Medication> medications  = new HashSet<>();

    private String companyName;

    public Manufacturer() {
    }

    public Manufacturer(String country, String companyName) {
        this.country = country;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }

    public void removeMedication(Medication medication) {
        this.medications.remove(medication);
    }
}
