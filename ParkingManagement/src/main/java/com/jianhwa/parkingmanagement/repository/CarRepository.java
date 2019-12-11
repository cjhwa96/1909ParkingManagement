package com.jianhwa.parkingmanagement.repository;

import com.jianhwa.parkingmanagement.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, String> {

    public List<Car> findAllByUserId(String userId);

}
