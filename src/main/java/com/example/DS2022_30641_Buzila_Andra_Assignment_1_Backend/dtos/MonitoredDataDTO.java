package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.MonitoredData;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class MonitoredDataDTO {
    private int id;
    private LocalDateTime timeStamp;
    private Float energyConsumption;
    private int idDevice;

    public MonitoredDataDTO() {
    }

    public MonitoredDataDTO(int id, LocalDateTime timeStamp, Float energyConsumption, int idDevice) {
        this.id = id;
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

    public void setEnergyConsumption(Float energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

}
