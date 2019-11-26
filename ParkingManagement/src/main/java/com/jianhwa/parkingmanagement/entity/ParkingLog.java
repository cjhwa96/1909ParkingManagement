package com.jianhwa.parkingmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ParkingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String parkingSpace;
    private String carPlateNum;
    private Date bookingTime, arrivalTime, departureTime;
    private double cost;

    public ParkingLog(String parkingSpace, String carPlateNum) {
        this.parkingSpace = parkingSpace;
        this.carPlateNum = carPlateNum;
    }

    public void booking(){
        bookingTime = new Date();
    }

    public void arrived(){
        arrivalTime = new Date();
    }

    public void departed(){
        departureTime = new Date();

    }

}
