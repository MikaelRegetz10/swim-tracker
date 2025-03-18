package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.entities.user.Users;
import com.swimtracker.swimtracker.entities.user.UsersResponseDTO;
import com.swimtracker.swimtracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository repository;

    public List<Users> getAll(){
        List<Users> user = repository.findAll();
        System.out.println(user);
        return repository.findAll();
    }

}
