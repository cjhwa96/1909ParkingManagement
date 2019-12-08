package com.jianhwa.parkingmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ParkingTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userId;
    private String carPlateNum;
    private String parkingSpace;
    private Date bookingTime, arrivalTime, departureTime;
    private double cost;

    public ParkingTicket() {
    }

    public ParkingTicket(String userId, String carPlateNum) {
        this.userId = userId;
        this.carPlateNum = carPlateNum;
    }

    public void booking(){
        bookingTime = new Date();
    }

    public void arrived(String parkingSpace){
        this.parkingSpace = parkingSpace;
        arrivalTime = new Date();
    }

    public void departed(){
        departureTime = new Date();
        if (this.arrivalTime != null){
            this.cost = (((arrivalTime.getTime() - bookingTime.getTime()) / (60*60*1000)) * 3 )+ (((departureTime.getTime() - arrivalTime.getTime()) / (60*60*1000)) * 2 );
        } else {
            this.cost = ((departureTime.getTime() - bookingTime.getTime() / (60*60*1000)) * 3);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCarPlateNum() {
        return carPlateNum;
    }

    public void setCarPlateNum(String carPlateNum) {
        this.carPlateNum = carPlateNum;
    }

    public String getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(String parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
