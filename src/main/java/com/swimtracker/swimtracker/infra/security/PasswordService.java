package com.swimtracker.swimtracker.infra.security;

import com.swimtracker.swimtracker.entities.user.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Value("${api.security.default.password}")
    private String defaultPassword;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Boolean verifyPassword(UserDetails user) {
        return passwordEncoder.matches(defaultPassword, user.getPassword());
    }

}
