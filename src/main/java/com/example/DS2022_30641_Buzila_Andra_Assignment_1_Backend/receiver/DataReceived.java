package com.example.DS2022_30641_Buzila_Andra_Assignment_1_Backend.receiver;

import java.util.Date;

public class DataReceived {
    private Float measurement_value;
    private int device_id;
    private Date timestamp;

    public DataReceived(){

    }

    public DataReceived(Float measurement_value, int device_id, Date timestamp){
        this.measurement_value = measurement_value;
        this.device_id = device_id;
        this.timestamp = timestamp;
    }

    public Float getMeasurement_value() {
        return measurement_value;
    }

    public void setMeasurement_value(Float measurement_value) {
        this.measurement_value = measurement_value;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
