package com.swimtracker.swimtracker.controller;

import com.swimtracker.swimtracker.entities.coach.Coach;
import com.swimtracker.swimtracker.entities.user.*;
import com.swimtracker.swimtracker.infra.security.TokenService;
import com.swimtracker.swimtracker.repository.CoachRepository;
import com.swimtracker.swimtracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registro-tecnico")
    public ResponseEntity<?> register(@RequestBody RegisterCoachDTO data){
        if(this.usersRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUser = new Users(data.login(), encryptedPassword, data.role());
        Coach newCoach = new Coach(newUser, data.name(), data.team());

        this.usersRepository.save(newUser);
        this.coachRepository.save(newCoach);

        return ResponseEntity.ok().build();
    }



}
