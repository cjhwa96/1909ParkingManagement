package com.jianhwa.parkingmanagement.entity;

public class ParkingSpace {
    private String ps_num;
    private boolean isEmpty;
    private String carPlateNum;

    public ParkingSpace() {

    }

    public ParkingSpace(String parking_space_num) {
        ps_num = parking_space_num;
        isEmpty = true;
        carPlateNum = "";
    }

    public void departed() {
        isEmpty = true;
        carPlateNum = "";
    }

    public void arrived(String car_plate_num){
        isEmpty = false;
        carPlateNum = car_plate_num;
    }

    public String getParkingSpaceNum() {
        return ps_num;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public String getCarPlateNum() {
        return carPlateNum;
    }
}
