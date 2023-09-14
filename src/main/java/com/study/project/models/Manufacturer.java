package com.study.project.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String country;

    @OneToMany(mappedBy="manufacturer")
    private Set<Medication> medications;

    private String companyName;

    public Manufacturer() {
    }

    public Manufacturer(String country, String companyName) {
        this.country = country;
        this.companyName = companyName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }

    public void removeMedication(Medication medication) {
        this.medications.remove(medication);
    }
}
