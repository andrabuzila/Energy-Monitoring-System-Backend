package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "monitoredData")
public class MonitoredData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "timeStamp", nullable = false)
    private LocalDateTime timeStamp;

    @Column(name = "energyConsumption", nullable = false)
    private Float energyConsumption;

    @Column(name = "idDevice", nullable = false)
    private int idDevice;

    public MonitoredData() {
    }

    public MonitoredData(LocalDateTime timeStamp, Float energyConsumption, int idDevice) {
        this.timeStamp = timeStamp;
        this.energyConsumption = energyConsumption;
        this.idDevice = idDevice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Float getEnergyConsumption() {
        return energyConsumption;
    }

    public void setTimeStamp(Float energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }
}
