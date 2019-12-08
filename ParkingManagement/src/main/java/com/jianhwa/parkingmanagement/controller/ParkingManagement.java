package com.jianhwa.parkingmanagement.controller;

import com.jianhwa.parkingmanagement.entity.ParkingTicket;
import com.jianhwa.parkingmanagement.entity.ParkingSpace;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Queue;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/parking") // This means URL's start with /login (after Application path)
public class ParkingManagement {
    private static final int BOOKING_LIMIT = 5;
    private static final int TOTAL_NUMBER_OF_PARKING = 20;
    private static ParkingSpace parkingSpaceManager[] = new ParkingSpace[TOTAL_NUMBER_OF_PARKING];
    private static final String[] parking_space_num = {"A1", "A2", "A3", "A4", "A5",
                                                       "B1", "B2", "B3", "B4", "B5",
                                                       "C1", "C2", "C3", "C4", "C5",
                                                       "D1", "D2", "D3", "D4", "D5"};

    private static int index, driveInCounter, bookingCounter;
    private static HashMap<String, ParkingTicket> carTickets;
    private static Queue<ParkingTicket> drive_in_queue;


    public ParkingManagement() {
        for(int i = 0; i < TOTAL_NUMBER_OF_PARKING; i++){
            ParkingSpace newPS = new ParkingSpace(parking_space_num[i]);
            parkingSpaceManager[i] = newPS;
        }
        index = 0;
        driveInCounter = 0;
        bookingCounter = 0;
    }

    @GetMapping(path = "{user}/driveIn")
    public ParkingTicket driveIn(@PathVariable("user") String userId, String car_plate){
        if (carTickets.get(car_plate) == null){
            if ( checkSpaceAvailability()){
                return checkIn(userId, car_plate);
            } else {
                return queueUp(userId, car_plate);
            }
        } else {
            return carTickets.get(car_plate);
        }
    }

    /** true  --> parking available, ready to check in
        false --> parking unavailable, queue up  **/
    private boolean checkSpaceAvailability(){
        if (driveInCounter + bookingCounter >= TOTAL_NUMBER_OF_PARKING) { //the parking is full
            return false;
        } else {
            sortingIndex();
            return true;
        }
    }

    private void sortingIndex(){
        if (!parkingSpaceManager[index].isEmpty()) {
            for (int i = index; i < TOTAL_NUMBER_OF_PARKING; i++) {
                if (parkingSpaceManager[i].isEmpty()) {
                    index = i;
                }
            }
        }
    }

    private ParkingTicket checkIn(String userId, String car_plate){
        ParkingTicket newArrival = new ParkingTicket(userId, car_plate);
        newArrival.booking();
        newArrival.arrived(parkingSpaceManager[index].getPs_num());
        parkingSpaceManager[index].arrived(car_plate, newArrival.getId());
        index++;
        carTickets.put(car_plate,newArrival);
        return newArrival;
    }

    private ParkingTicket queueUp(String userId, String car_plate){
        ParkingTicket newArrival = new ParkingTicket(userId, car_plate);
        drive_in_queue.add(newArrival);
        return newArrival;
    }

    @GetMapping(path = "{car_plate}/checkOut")
    public ParkingTicket checkOut( @PathVariable("car_plate") String car_plate){
        if (carTickets.get(car_plate) != null) {
            ParkingTicket parkingTicket = carTickets.get(car_plate);
            parkingTicket.departed();
            for (int i = 0; i < index; i++) {
                if (parking_space_num[i].equals(parkingTicket.getParkingSpace())){
                    index = i;
                    break;
                }
            }
            carTickets.remove(car_plate);
//            insert parkingTicket to database
            return parkingTicket;
        } else {
            return null;
        }
    }

    @GetMapping(path = "{user}/booking")
    public ParkingTicket booking (@PathVariable("user") String userId, String car_plate){
        if (carTickets.get(car_plate) == null) {
            if (bookingCounter <= BOOKING_LIMIT && checkSpaceAvailability()){
                bookingCounter++;
                ParkingTicket newTicket = new ParkingTicket(userId, car_plate);
                newTicket.booking();
                carTickets.put(car_plate, newTicket);
                return carTickets.get(car_plate);
            }
        }
        return null;
    }

    @GetMapping(path = "{user}/expressIn")
    public ParkingTicket expressIn (@PathVariable("user") String userId, String car_plate){
        if (carTickets.get(car_plate) != null && checkSpaceAvailability()){
            sortingIndex();
            ParkingTicket newArrival = carTickets.get(car_plate);
            carTickets.remove(car_plate);
            newArrival.arrived(parkingSpaceManager[index].getPs_num());
            parkingSpaceManager[index].arrived(car_plate, newArrival.getId());
            index++;
            bookingCounter--;
            carTickets.put(car_plate,newArrival);
            return carTickets.get(car_plate);
        }else {
            return null;
        }
    }

    @GetMapping(path = "{car_plate}/checkCar")
    public ParkingTicket checkCar (@PathVariable("car_plate") String car_plate){
        if (carTickets.get(car_plate) != null){
            return carTickets.get(car_plate);
        }else {
            return null;
        }
    }

}
