package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.receiver.Receiver;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.MonitoredDataRepository;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class Ds202230641BuzilaAndraAssignment1BackendApplication {

	private static UserService userService;
	@Autowired
	public Ds202230641BuzilaAndraAssignment1BackendApplication(UserService userService){
		this.userService = userService;
	}
	public static void main(String[] args) throws IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
		SpringApplication.run(Ds202230641BuzilaAndraAssignment1BackendApplication.class, args);
		Receiver receiver = new Receiver(userService);
		receiver.receiverMethod();
	}

}
