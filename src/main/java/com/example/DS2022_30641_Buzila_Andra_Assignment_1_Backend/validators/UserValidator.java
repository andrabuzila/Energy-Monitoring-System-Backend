package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.validators;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.User;
import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean CheckIfUserExist(int id) {
        User user = userRepository.findAll().stream().filter(currentUser -> currentUser.getId() == id).findFirst().get();
        if(user != null){
            return true;
        }
        return false;
    }
}
