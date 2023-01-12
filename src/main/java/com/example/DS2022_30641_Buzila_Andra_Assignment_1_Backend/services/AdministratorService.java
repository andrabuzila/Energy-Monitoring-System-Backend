package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.services;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.*;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.Device;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.MonitoredData;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.User;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.UserDevice;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.DeviceRepository;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.UserDeviceRepository;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.UserRepository;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdministratorService {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final UserDeviceRepository userDeviceRepository;

    @Autowired
    public AdministratorService(UserRepository userRepository, DeviceRepository deviceRepository, UserDeviceRepository userDeviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.userDeviceRepository = userDeviceRepository;
    }

    public List<UserDTO> GetUsers () {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserBuilder::userToUserDTO)
                .collect(Collectors.toList());
    }

    public void Create (UserDTO userDTO) {
        String salt = GenerateRandomSalt();
        String password = GenerateHashedPassword(userDTO.getPassword(),salt);
        userDTO.setSalt(salt);
        userDTO.setPassword(password);
        User user = UserBuilder.UserDTOToUser(userDTO);
        userRepository.save(user);
    }

    public boolean TryDelete(int id)
    {
        User user = userRepository.findById(id).get();
        if (user == null)
        {
            return false;
        }
        else
        {
            userRepository.delete(user);
            return true;
        }
    }

    public void Update(UserDTO user)
    {
        int userId = user.getId();
        UserValidator userValidator = new UserValidator(userRepository);
        if (userValidator.CheckIfUserExist(userId))
        {
            User currentUser = userRepository.findById(userId).get();
            userRepository.delete(currentUser);
            if(!currentUser.getPassword().equals(GenerateHashedPassword(user.getPassword(), currentUser.getSalt())))
            {
                String newPasswordHash = GenerateHashedPassword(user.getPassword(), currentUser.getSalt());
                currentUser.setPassword(newPasswordHash);
            }
            currentUser.setName(user.getName());
            currentUser.setEmail(user.getEmail());
            currentUser.setRole(user.getRole());
            userRepository.save(currentUser);
        }
    }

    public UserDTO GetByEmail(String email){
        List<User> users = userRepository.findAll();
        return UserBuilder.userToUserDTO(users.stream().filter(user -> user.getEmail().equals(email)).findFirst().get());
    }

    public void CreateDevice(DeviceDTO deviceDTO)
    {
        Device device = DeviceBuilder.DeviceDTOToDevice(deviceDTO);
        deviceRepository.save(device);
    }

    public List<DeviceDTO> GetDevices () {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream()
                .map(DeviceBuilder::deviceToDeviceDTO)
                .collect(Collectors.toList());
    }

    public boolean TryDeleteDevice(int id)
    {
        Device device = deviceRepository.findById(id).get();
        if (device == null)
        {
            return false;
        }
        else
        {
            deviceRepository.delete(device);
            return true;
        }
    }

    public void CreateMapping(UserDeviceDTO userDeviceDTO) throws IOException {
        Properties properties = new Properties();
        UserDevice userDevice = UserDeviceBuilder.DTOToUserDevice(userDeviceDTO);
        userDeviceRepository.save(userDevice);
        List<UserDevice> allMappings = userDeviceRepository.findAll();
        if(allMappings.size() == 1){
            properties.setProperty("device_id", Integer.toString(userDevice.getIdDevice()));
            properties.store(new FileOutputStream(("conf.properties")), "");
        }
    }

    public List<DeviceDTO> GetDevicesForASpecificUser (int idUser) {
        List<Integer> userDevicesIds = userDeviceRepository.findAll().stream().filter(usrdev -> usrdev.getIdUser()==idUser).map(userdevice -> userdevice.getIdDevice()).collect(Collectors.toList());
        List<Device> devices = deviceRepository.findAll();
        return devices.stream()
                .map(DeviceBuilder::deviceToDeviceDTO).filter(device -> !userDevicesIds.contains(device.getId()))
                .collect(Collectors.toList());
    }

    public int GetDeviceIdByName(String name) {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream().filter(device -> device.getDescription().equals(name)).findFirst().get().getId();
    }

    private static String GenerateRandomSalt()
    {
        return BCrypt.gensalt();
    }

    private static String GenerateHashedPassword(String password, String salt)
    {
        return BCrypt.hashpw(password,salt);
    }

}
