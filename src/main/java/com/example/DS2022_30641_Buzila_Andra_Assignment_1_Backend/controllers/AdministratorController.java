package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.controllers;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.DeviceDTO;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.UserDTO;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.UserDeviceDTO;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/Administrator")
public class AdministratorController {

    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService){
        this.administratorService = administratorService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> dtos = administratorService.GetUsers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("AddUser")
    public boolean insertUser(@Valid @RequestBody UserDTO userDTO) {
        administratorService.Create(userDTO);
        return true;
    }

    @DeleteMapping(value = "DeleteUser/{id}")
    public boolean deleteUser(@PathVariable("id") int userId){
        administratorService.TryDelete(userId);
        return true;
    }

    @GetMapping(value = "GetByEmail/{email}")
    public ResponseEntity<UserDTO> getPerson(@PathVariable("email") String email) {
        UserDTO dto = administratorService.GetByEmail(email);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("UpdateUser")
    public boolean updateUser(@Valid @RequestBody UserDTO user) {
        administratorService.Update(user);
        return true;
    }

    @PostMapping("AddDevice")
    public boolean insertDevice(@Valid @RequestBody DeviceDTO deviceDTO) {
        administratorService.CreateDevice(deviceDTO);
        return true;
    }

    @GetMapping("GetDevices")
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        List<DeviceDTO> dtos = administratorService.GetDevices();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping(value = "DeleteDevice/{id}")
    public boolean deleteDevice(@PathVariable("id") int deviceId){
        administratorService.TryDeleteDevice(deviceId);
        return true;
    }

    @PostMapping("MapUserDevice")
    public boolean insertMappingUserDevice(@Valid @RequestBody UserDeviceDTO userDeviceDTO) throws IOException {
        administratorService.CreateMapping(userDeviceDTO);
        return true;
    }

    @GetMapping(value = "GetDevicesForUser/{idUser}")
    public List<DeviceDTO> getDevicesForUser(@PathVariable("idUser") int idUser){
        return administratorService.GetDevicesForASpecificUser(idUser);
    }

    @GetMapping(value = "GetDeviceIdByName/{name}")
    public int getDevicesForUser(@PathVariable("name") String name){
        return administratorService.GetDeviceIdByName(name);
    }

}
