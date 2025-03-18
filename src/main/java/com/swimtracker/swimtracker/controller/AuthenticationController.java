package com.swimtracker.swimtracker.controller;

import com.swimtracker.swimtracker.entities.user.AuthenticationDTO;
import com.swimtracker.swimtracker.entities.user.LoginResponseDTO;
import com.swimtracker.swimtracker.entities.user.RegisterDTO;
import com.swimtracker.swimtracker.entities.user.Users;
import com.swimtracker.swimtracker.infra.security.PasswordService;
import com.swimtracker.swimtracker.infra.security.TokenService;
import com.swimtracker.swimtracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);


        String token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/registro")
    public ResponseEntity register(@RequestBody RegisterDTO data){
        if(this.usersRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUser = new Users(data.login(), encryptedPassword, data.role());

        this.usersRepository.save(newUser);

        return ResponseEntity.ok().build();

    }

}
