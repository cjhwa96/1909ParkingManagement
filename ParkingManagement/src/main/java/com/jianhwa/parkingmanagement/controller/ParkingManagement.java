package com.jianhwa.parkingmanagement.controller;

import com.jianhwa.parkingmanagement.entity.ParkingLog;
import com.jianhwa.parkingmanagement.entity.ParkingSpace;

public class ParkingManagement {
    private static final int BOOKING_LIMIT = 5;
    private static final int TOTAL_NUMBER_OF_PARKING = 20;
    private ParkingSpace parkingManager[] = new ParkingSpace[TOTAL_NUMBER_OF_PARKING];
    private String[] parking_space_num = {"A1", "A2", "A3", "A4", "A5",
                                          "B1", "B2", "B3", "B4", "B5",
                                          "C1", "C2", "C3", "C4", "C5",
                                          "D1", "D2", "D3", "D4", "D5"};
    private int index, counter, bookingCounter;


    public ParkingManagement() {
        for(int i = 0; i < TOTAL_NUMBER_OF_PARKING; i++){
            ParkingSpace newPS = new ParkingSpace(parking_space_num[i]);
            parkingManager[i] = newPS;
        }
        index = 0;
        counter = 0;
        bookingCounter = 0;
    }

    public void driveIn(String car_plate){
        if ( checkSpaceAvailability() ){
            checkIn(car_plate);
        } else {
            queueUp(car_plate);
        }
    }

    /** true  --> parking available, ready to check in
        false --> parking unavailable, queue up  **/
    private boolean checkSpaceAvailability(){
        if (counter+bookingCounter >= TOTAL_NUMBER_OF_PARKING) { //the parking is full
            return false;
        }

        if (!parkingManager[index].isEmpty()) {
            for (int i = index; i < TOTAL_NUMBER_OF_PARKING; i++) {
                if (parkingManager[i].isEmpty()) {
                    index = i;
                }
            }
        }

        return true;
    }

    private void checkIn(String car_plate){
        ParkingLog newArrival = new ParkingLog(parkingManager[index].getParkingSpaceNum(), car_plate);
    }

    private void queueUp(String car_plate){

    }
}
