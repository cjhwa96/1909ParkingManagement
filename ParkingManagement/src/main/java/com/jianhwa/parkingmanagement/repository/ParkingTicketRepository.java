package com.jianhwa.parkingmanagement.repository;

import com.jianhwa.parkingmanagement.entity.ParkingTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParkingTicketRepository extends CrudRepository<ParkingTicket, Long> {

    public List<ParkingTicket> findAllByCarPlateNum (String car_plate);

    public List<ParkingTicket> findAllByUserId (String userId);
}
