package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.Device;

public class DeviceBuilder {
    private DeviceBuilder () {

    }

    public static DeviceDTO deviceToDeviceDTO(Device device){
        return new DeviceDTO(device.getId(), device.getDescription(), device.getAddress(), device.getMaximumEnergyConsumption());
    }

    public static Device DeviceDTOToDevice (DeviceDTO deviceDTO) {
        return new Device(deviceDTO.getDescription(), deviceDTO.getAddress(), deviceDTO.getMaximumEnergyConsumption());
    }
}
