package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.dtos;

import com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities.User;

public class UserBuilder {
    private UserBuilder () {

    }

    public static UserDTO userToUserDTO(User user){
        return new UserDTO(user.getId(), user.getName(), user.getRole(), user.getEmail(), user.getPassword(), user.getSalt());
    }

    public static User UserDTOToUser (UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getRole(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getSalt());
    }
}
