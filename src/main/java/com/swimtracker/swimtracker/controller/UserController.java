package com.swimtracker.swimtracker.controller;

import com.swimtracker.swimtracker.entities.user.Users;
import com.swimtracker.swimtracker.entities.user.UsersResponseDTO;
import com.swimtracker.swimtracker.repository.UsersRepository;
import com.swimtracker.swimtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UsersRepository service;

    @GetMapping
    public List<Users> returnUser(){
        for (Users user : service.findAll()) {
            System.out.println(user.getRoles());
        }

        return service.findAll();
    }
}
