package com.study.project.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expirationDate;
    private Long amount;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "medication")
    Set<MedicationFromIllness> medicationFromIllnesses  = new HashSet<>();

    public Medication() {
    }

    public Medication(String name, Long price, LocalDate expirationDate, Long amount) {
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.amount = amount;
    }

    public Medication(String name, Long price, Manufacturer manufacturer, LocalDate expirationDate, Long amount) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.expirationDate = expirationDate;
        this.amount = amount;
    }
}
