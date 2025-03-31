package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.exceptions.UserNotFoundException;
import com.swimtracker.swimtracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizationService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return usersRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
