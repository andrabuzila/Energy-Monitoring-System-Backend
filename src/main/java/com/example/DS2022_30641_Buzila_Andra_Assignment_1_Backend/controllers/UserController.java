package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.controllers;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.DeviceDTO;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.LoginDTO;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.MonitoredDataDTO;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/User")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("IsUserVerified")
    public boolean isUserVerified(@Valid @RequestBody LoginDTO loginDTO){
        return this.userService.verifyUser(loginDTO.getEmail(), loginDTO.getPassword());
    }

    @PostMapping("UserRole/{email}")
    public String UserRole(@PathVariable("email") String email){
        if(!userService.UserRole(email).equals("error")){
            return userService.UserRole(email);
        }
        return "";
    }

    @PostMapping("GetUserId")
    public int GetUserId(@Valid @RequestBody LoginDTO loginDTO) {
        return userService.GetUserId(loginDTO);
    }

    @GetMapping("IsUserAdmin/{userId}")
    public boolean IsUserAdmin(@PathVariable("userId") int userId){
        return userService.UserRoleById(userId).equals("administrator");
    }

    @GetMapping("IsUserSimpleUser/{userId}")
    public boolean IsUserSimpleUser(@PathVariable("userId") int userId){
        return userService.UserRoleById(userId).equals("user");
    }

    @GetMapping("GetDevices/{userId}")
    public List<DeviceDTO> GetDevicesForUser(@PathVariable("userId") int userId) {
        return userService.GetDevices(userId);
    }

    @GetMapping("GetMonitoredData/{deviceId}")
    public List<MonitoredDataDTO> GetMonitoredData(@PathVariable("deviceId") int deviceId) {
        return userService.GetMonitoredData(deviceId);
    }

    @PostMapping("PostMonitoredData")
    public void PostMonitoredData(@Valid @RequestBody MonitoredDataDTO monitoredDataDTO){
        userService.AddMonitoredData(monitoredDataDTO);
    }

    @GetMapping("GetNotification/{deviceId}")
    public List<String> GetNotification(@PathVariable("deviceId") int deviceId) {
        return userService.GetNotification(deviceId);
    }

}
