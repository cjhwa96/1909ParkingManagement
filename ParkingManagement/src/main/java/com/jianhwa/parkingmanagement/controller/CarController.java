package com.jianhwa.parkingmanagement.controller;

import com.jianhwa.parkingmanagement.entity.Car;
import com.jianhwa.parkingmanagement.entity.ParkingTicket;
import com.jianhwa.parkingmanagement.repository.CarRepository;
import com.jianhwa.parkingmanagement.repository.ParkingTicketRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/cars")
public class CarController {
    private CarRepository carRepository;
    private ParkingTicketRepository parkingTicketRepository;

    @GetMapping(path = "{user}/getCarList")
    public List<Car> getCarList(@PathVariable("user") String userId){
        List<Car> carList = carRepository.findAllByUserId(userId);  //database get cars of user
        return carList;
    }

    @GetMapping(path = "{car_plate}/checkCarLog")
    public List<ParkingTicket> getCarLogList (@PathVariable("car_plate") String car_plate){
        List<ParkingTicket> carLogList = parkingTicketRepository.findAllByCarPlateNum(car_plate);  //database get car logs
        return carLogList;
    }

    @PostMapping(path = "deleteCar")
    public String deleteCar (String car_plate){
        carRepository.deleteById(car_plate);  //delete car from database
        return "Success";
    }

    @PostMapping(path = "addCar")
    public String addCar (Car newCar){
        carRepository.save(newCar);  //add car from database
        return "Success";
    }
}
