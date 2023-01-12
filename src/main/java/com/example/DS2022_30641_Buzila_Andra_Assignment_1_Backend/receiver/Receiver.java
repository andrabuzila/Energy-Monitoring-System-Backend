package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.receiver;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.MonitoredDataBuilder;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos.MonitoredDataDTO;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.MonitoredData;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.MonitoredDataRepository;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.services.UserService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class Receiver {
    private final String QUEUE_NAME = "hello";
    @Autowired
    private final UserService userService;

    @Autowired
    public Receiver(UserService userService){

        this.userService = userService;
    }

    public void receiverMethod() throws IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://hohbspor:qKGNgiD2khvKWLaNYwGsXX_4KBjFA5Rr@goose.rmq2.cloudamqp.com/hohbspor");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            String[] messageParsed = message.split(",");
            MonitoredDataDTO data = new MonitoredDataDTO();
            for(String m: messageParsed){
                String[] eachMessage = m.split("\"");
                if(eachMessage[1].equals("measurement_value")){
                    data.setEnergyConsumption(Float.parseFloat(eachMessage[3]));
                }
                if(eachMessage[1].equals("device_id")){
                    data.setIdDevice(Integer.parseInt(eachMessage[3]));
                }
                if(eachMessage[1].equals("timestamp")){
                    LocalDateTime timestamp=LocalDateTime.parse(eachMessage[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    data.setTimeStamp(timestamp);
                }

            }
            userService.AddMonitoredData(data);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
