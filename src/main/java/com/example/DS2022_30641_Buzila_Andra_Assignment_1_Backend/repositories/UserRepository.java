package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

}
