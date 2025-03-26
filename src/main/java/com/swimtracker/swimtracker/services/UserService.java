package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.entities.user.TradePasswordDTO;
import com.swimtracker.swimtracker.entities.user.Users;
import com.swimtracker.swimtracker.exceptions.IncompleteNameException;
import com.swimtracker.swimtracker.exceptions.InvalidPasswordException;
import com.swimtracker.swimtracker.infra.security.PasswordService;
import com.swimtracker.swimtracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordService passwordService;

    public List<Users> getAll(){
        List<Users> user = repository.findAll();
        System.out.println(user);
        return repository.findAll();
    }

    @Transactional
    public boolean tradePassword(Users user, TradePasswordDTO data){
        if (!passwordService.verifyPassword(data.defaultPassword(), user.getPassword()) || !data.newPassword().equals(data.confirmPassword())) throw new InvalidPasswordException();

        user.setPassword(passwordService.encodePassword(data.newPassword()));
        repository.save(user);
        return true;
    }

    protected String generateLogin(String completeName) {
        String[] parts = completeName.trim().split("\\s+");
        if (parts.length < 2) {
            throw new IncompleteNameException();
        }
        String firstName = parts[0].toLowerCase();
        String lastName = parts[parts.length - 1].toLowerCase();
        return firstName + "." + lastName;
    }

}
