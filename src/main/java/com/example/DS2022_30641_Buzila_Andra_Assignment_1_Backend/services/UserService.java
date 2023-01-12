package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.services;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.*;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.Device;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.MonitoredData;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.User;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.DeviceRepository;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.MonitoredDataRepository;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.UserDeviceRepository;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDeviceRepository userDeviceRepository;
    private final DeviceRepository deviceRepository;
    private final MonitoredDataRepository monitoredDataRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserDeviceRepository userDeviceRepository, DeviceRepository deviceRepository, MonitoredDataRepository monitoredDataRepository) {
        this.userRepository = userRepository;
        this.userDeviceRepository = userDeviceRepository;
        this.deviceRepository = deviceRepository;
        this.monitoredDataRepository = monitoredDataRepository;
    }

    public boolean verifyUser(String email, String password)
    {
        List<User> users = userRepository.findAll();
        UserDTO currentUser = UserBuilder.userToUserDTO(users.stream().filter(user -> user.getEmail().equals(email)).findFirst().get());
        if(currentUser != null)
        {
            String currentUserHashedPass = GenerateHashedPassword(password, currentUser.getSalt());
            if (currentUser.getPassword().equals(currentUserHashedPass))
            {
                return true;
            }
        }
        return false;
    }

    public String UserRole(String email) {
        List<User> users = userRepository.findAll();
        UserDTO currentUser = UserBuilder.userToUserDTO(users.stream().filter(user -> user.getEmail().equals(email)).findFirst().get());

        if(currentUser != null){
            return currentUser.getRole();
        }
        return "error";
    }

    public String UserRoleById(int id) {
        UserDTO currentUser = UserBuilder.userToUserDTO(userRepository.findById(id).get());
        if(currentUser.getRole().equals("administrator")){
            return "administrator";
        }
        return "user";
    }

    public int GetUserId(LoginDTO loginDTO){
        List<User> users = userRepository.findAll();
        UserDTO currentUser = UserBuilder.userToUserDTO(users.stream().filter(user -> user.getEmail().equals(loginDTO.getEmail())).findFirst().get());
        if(currentUser != null){
            String currentUserHashedPass = GenerateHashedPassword(loginDTO.getPassword(),currentUser.getSalt());
            if(currentUser.getPassword().equals(currentUserHashedPass)){
                return currentUser.getId();
            }
        }
        return 0;
    }

    public List<DeviceDTO> GetDevices(int idUser){
        List<Integer> devicesIds = userDeviceRepository.findAll().stream().filter(userDevice -> userDevice.getIdUser() == idUser).map(usrDev -> usrDev.getIdDevice()).collect(Collectors.toList());
        return deviceRepository.findAll().stream().filter(device -> devicesIds.contains(device.getId())).map(DeviceBuilder::deviceToDeviceDTO).collect(Collectors.toList());
    }

    public List<MonitoredDataDTO> GetMonitoredData(int idDevice){
        return monitoredDataRepository.findAll().stream().filter(monitoredData -> monitoredData.getIdDevice() == idDevice).map(MonitoredDataBuilder::monitoredDataToDTO).collect(Collectors.toList());
    }


    public void AddMonitoredData(MonitoredDataDTO monitoredDataDTO){
        MonitoredData data = MonitoredDataBuilder.DTOToMonitoredData(monitoredDataDTO);
        monitoredDataRepository.save(data);
    }

    public List<String> GetNotification(int deviceId){
        List<String> notifications = new ArrayList<>();
        List<MonitoredDataDTO> allData = monitoredDataRepository.findAll().stream().filter(monitoredData -> monitoredData.getIdDevice() == deviceId).map(MonitoredDataBuilder::monitoredDataToDTO).collect(Collectors.toList());
        Float deviceMaxEnergyConsumption = deviceRepository.findById(deviceId).get().getMaximumEnergyConsumption();
        for(MonitoredDataDTO data: allData){
            String notification;
            if(data.getEnergyConsumption() <= deviceMaxEnergyConsumption){
                notification = "The current energy consumption did not exceed the maximum value. Current value: " + data.getEnergyConsumption() + ", Maximum value: " + deviceMaxEnergyConsumption;
            } else {
                notification = "The current energy consumption EXCEEDED the maximum value. Current value: " + data.getEnergyConsumption() + ", Maximum value: " + deviceMaxEnergyConsumption;
            }
            notifications.add(notification);
        }

        return notifications;
    }

    private static String GenerateHashedPassword(String password, String salt)
    {
        return BCrypt.hashpw(password,salt);
    }

}
