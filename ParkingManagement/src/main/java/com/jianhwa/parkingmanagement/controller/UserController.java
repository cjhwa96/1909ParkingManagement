package com.jianhwa.parkingmanagement.controller;

import com.jianhwa.parkingmanagement.entity.User;
import com.jianhwa.parkingmanagement.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/users")
public class UserController {
    private UserRepository userRepository;

    @GetMapping(path = "{user}/checkExist")
    public Boolean exist(@PathVariable("user") String userId){
        return userRepository.findById(userId).isPresent();
    }

    @GetMapping(path = "{user}/getUserProfile")
    public User getUserProfile(@PathVariable("user") String userId){
        if (userRepository.findById(userId).isPresent())
            return userRepository.findById(userId).get();
        else
            return null;
    }

    @PostMapping(path = "{user}/editUserProfile")
    public String editUserProfile(@PathVariable("user") String userId, User newProfile){
        userRepository.save(newProfile); // need delete old profile in database first????
        return "Success";
    }


}
