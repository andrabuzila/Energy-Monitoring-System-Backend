package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.MonitoredData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoredDataRepository extends JpaRepository<MonitoredData, Integer> {
}
