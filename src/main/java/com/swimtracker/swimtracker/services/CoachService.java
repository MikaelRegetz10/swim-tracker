package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.athlete.RegisterAthleteDTO;
import com.swimtracker.swimtracker.entities.coach.Coach;
import com.swimtracker.swimtracker.entities.user.UserRoles;
import com.swimtracker.swimtracker.entities.user.Users;
import com.swimtracker.swimtracker.exceptions.IncompleteNameException;
import com.swimtracker.swimtracker.infra.security.PasswordService;
import com.swimtracker.swimtracker.repository.AthleteRepository;
import com.swimtracker.swimtracker.repository.CoachRepository;
import com.swimtracker.swimtracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CoachService {
    @Value("${api.security.default.athlete.password}")
    private String athletePassword;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserService userService;


    @Transactional
    public Athlete registerAthlete(RegisterAthleteDTO data, Coach coach) {
        String encryptedPassword = passwordService.encodePassword(athletePassword);
        Users users = new Users(userService.generateLogin(data.name()), encryptedPassword, UserRoles.ATLETA);
        Athlete athlete = new Athlete(data.name(), data.category(), coach, users);

        userRepository.save(users);
        athleteRepository.save(athlete);

        return athlete;
    }



}
