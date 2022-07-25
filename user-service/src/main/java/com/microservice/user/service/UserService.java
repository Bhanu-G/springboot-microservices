package com.microservice.user.service;

import com.microservice.user.entity.Department;
import com.microservice.user.entity.ResponseDTO;
import com.microservice.user.entity.User;
import com.microservice.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return repository.save(user);
    }

    public ResponseDTO getUserWithDepartment(Long id) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseDTO responseDTO = new ResponseDTO();
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            return null;
        }
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.get().getDepartmentId(), Department.class);

        responseDTO.setUser(user.get());
        responseDTO.setDepartment(department);
        return responseDTO;
    }
}
