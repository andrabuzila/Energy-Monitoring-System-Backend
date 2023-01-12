package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos;


import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.MonitoredData;

public class MonitoredDataBuilder {

    public MonitoredDataBuilder () {

    }

    public static MonitoredDataDTO monitoredDataToDTO(MonitoredData monitoredData){
        return new MonitoredDataDTO(monitoredData.getId(), monitoredData.getTimeStamp(), monitoredData.getEnergyConsumption(), monitoredData.getIdDevice());
    }

    public static MonitoredData DTOToMonitoredData (MonitoredDataDTO monitoredDataDTO) {
        return new MonitoredData(monitoredDataDTO.getTimeStamp(), monitoredDataDTO.getEnergyConsumption(), monitoredDataDTO.getIdDevice());
    }
}
