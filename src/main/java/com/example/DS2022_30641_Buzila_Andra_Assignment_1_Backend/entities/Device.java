package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "devices")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "maximumEnergyConsumption", nullable = false)
    private Float maximumEnergyConsumption;

    public Device() {
    }

    public Device(String description, String address, Float maximumEnergyConsumption) {
        this.description = description;
        this.address = address;
        this.maximumEnergyConsumption = maximumEnergyConsumption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getMaximumEnergyConsumption() {
        return maximumEnergyConsumption;
    }

    public void setMaximumEnergyConsumption(Float maximumEnergyConsumption) {
        this.maximumEnergyConsumption = maximumEnergyConsumption;
    }

}
