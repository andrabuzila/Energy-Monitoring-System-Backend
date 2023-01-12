package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.Device;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.UserDevice;

public class UserDeviceBuilder {
    private UserDeviceBuilder () {

    }

    public static UserDeviceDTO userDeviceToDTO(UserDevice userDevice){
        return new UserDeviceDTO(userDevice.getId(), userDevice.getIdUser(), userDevice.getIdDevice());
    }

    public static UserDevice DTOToUserDevice (UserDeviceDTO userDeviceDTO) {
        return new UserDevice(userDeviceDTO.getIdUser(), userDeviceDTO.getIdDevice());
    }
}
