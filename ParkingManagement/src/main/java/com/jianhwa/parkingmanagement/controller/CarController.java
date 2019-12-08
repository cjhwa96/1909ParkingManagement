package com.jianhwa.parkingmanagement.controller;

import com.jianhwa.parkingmanagement.entity.Car;
import com.jianhwa.parkingmanagement.entity.ParkingTicket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/car")
public class CarController {

    @GetMapping(path = "{user}/getCarList")
    public List<Car> getCarList(@PathVariable("user") String userId){
        List<Car> carList = new ArrayList<Car>();
//        database get cars of user
        return carList;
    }

    @GetMapping(path = "{car_plate}/checkCarLog")
    public List<ParkingTicket> getCarLogList (@PathVariable("car_plate") String car_plate){
        List<ParkingTicket> carLogList = new ArrayList<ParkingTicket>();
//        database get car logs
        return carLogList;
    }

    @DeleteMapping(path = "{car_plate}")
    public void deleteCar (@PathVariable("car_plate") String car_plate){
//        delete car from database
    }

    @PostMapping(path = "addCar")
    public void addCar (Car newCar){
//        add car from database
    }
}
