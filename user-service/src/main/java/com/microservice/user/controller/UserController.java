package com.microservice.user.controller;

import com.microservice.user.entity.ResponseDTO;
import com.microservice.user.entity.User;
import com.microservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser of UserController");
        return service.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseDTO getUserWithDepartment(@PathVariable("id") Long id) {
        log.info("Inside getUserWithDepartment of UserController");
        return service.getUserWithDepartment(id);
    }
}
