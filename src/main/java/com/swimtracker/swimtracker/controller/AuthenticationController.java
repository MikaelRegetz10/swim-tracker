package com.swimtracker.swimtracker.controller;

import com.swimtracker.swimtracker.entities.coach.Coach;
import com.swimtracker.swimtracker.entities.user.*;
import com.swimtracker.swimtracker.exceptions.InvalidPasswordException;
import com.swimtracker.swimtracker.infra.security.PasswordService;
import com.swimtracker.swimtracker.infra.security.TokenService;
import com.swimtracker.swimtracker.repository.CoachRepository;
import com.swimtracker.swimtracker.repository.UsersRepository;
import com.swimtracker.swimtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private CoachRepository coachRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data){
        try{
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);
            String token = tokenService.generateToken((Users) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e){
            throw new InvalidPasswordException();
        }

    }

    @PostMapping("/registro-tecnico")
    public ResponseEntity<?> registerCoach(@RequestBody RegisterCoachDTO data){
        if(this.usersRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = passwordService.encodePassword(data.password());
        Users newUser = new Users(data.login(), encryptedPassword, data.role());
        Coach newCoach = new Coach(newUser, data.name(), data.team());

        this.usersRepository.save(newUser);
        this.coachRepository.save(newCoach);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registro")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO data){
        if(this.usersRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUser = new Users(data.login(), encryptedPassword, data.role());
        this.usersRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/trocar-senha")
    public ResponseEntity<?> tradePassword(@RequestBody TradePasswordDTO data){
        String subject = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails user = usersRepository.findByLogin(subject);
        if (!userService.tradePassword((Users) user, data)) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body("Senha alterada com sucesso");
    }


}
