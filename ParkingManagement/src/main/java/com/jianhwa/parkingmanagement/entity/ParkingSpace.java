package com.jianhwa.parkingmanagement.entity;

public class ParkingSpace {
    private String ps_num;
    private boolean isEmpty;
    private String carPlateNum;
    private long ticketId;

    public ParkingSpace() {

    }

    public ParkingSpace(String parking_space_num) {
        ps_num = parking_space_num;
        isEmpty = true;
        carPlateNum = "";
        ticketId = -1;
    }

    public void departed() {
        isEmpty = true;
        carPlateNum = "";
        ticketId = -1;
    }

    public void arrived(String car_plate_num, long parking_ticket_id){
        isEmpty = false;
        carPlateNum = car_plate_num;
        ticketId = parking_ticket_id;
    }

    public String getPs_num() {
        return ps_num;
    }

    public void setPs_num(String ps_num) {
        this.ps_num = ps_num;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getCarPlateNum() {
        return carPlateNum;
    }

    public void setCarPlateNum(String carPlateNum) {
        this.carPlateNum = carPlateNum;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
}
