package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="userDevices")
public class UserDevice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "idUser", nullable = false)
    private int idUser;

    @Column(name = "idDevice", nullable = false)
    private int idDevice;

    public UserDevice() {
    }

    public UserDevice(int idUser, int idDevice) {
        this.idUser = idUser;
        this.idDevice = idDevice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }
}
