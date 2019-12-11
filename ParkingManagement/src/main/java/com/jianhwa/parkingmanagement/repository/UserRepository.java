package com.jianhwa.parkingmanagement.repository;

import com.jianhwa.parkingmanagement.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {


}
