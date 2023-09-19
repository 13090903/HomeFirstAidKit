package com.study.project.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

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

    @OneToMany(mappedBy = "medication")
    Set<MedicationFromIllness> medicationFromIllnesses;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<MedicationFromIllness> getMedicationFromIllnesses() {
        return medicationFromIllnesses;
    }

    public void setMedicationFromIllnesses(Set<MedicationFromIllness> medicationFromIllnesses) {
        this.medicationFromIllnesses = medicationFromIllnesses;
    }
}
