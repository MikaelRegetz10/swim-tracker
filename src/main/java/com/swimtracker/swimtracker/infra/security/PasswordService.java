package com.swimtracker.swimtracker.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Value("${api.security.default.password}")
    private String defaultPassword;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Boolean verifyDefaultPassword(UserDetails user) {
        return passwordEncoder.matches(defaultPassword, user.getPassword());
    }

    public Boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
