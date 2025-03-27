package com.swimtracker.swimtracker.controller;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.athlete.RegisterAthleteDTO;
import com.swimtracker.swimtracker.entities.coach.Coach;
import com.swimtracker.swimtracker.entities.competition.CreateCompetitionDTO;
import com.swimtracker.swimtracker.entities.competition.ResponseCompetitionDTO;
import com.swimtracker.swimtracker.entities.partial.PartialResponseDTO;
import com.swimtracker.swimtracker.entities.partial.UpdatePartialDTO;
import com.swimtracker.swimtracker.entities.user.Users;
import com.swimtracker.swimtracker.repository.AthleteRepository;
import com.swimtracker.swimtracker.repository.CoachRepository;
import com.swimtracker.swimtracker.repository.UsersRepository;
import com.swimtracker.swimtracker.services.CoachService;
import com.swimtracker.swimtracker.services.CompetitionService;
import com.swimtracker.swimtracker.services.PartialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tecnico")
public class CoachController {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CoachService coachService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private PartialService partialService;


    @PostMapping("/adiconar-atleta")
    public ResponseEntity<?> addAthlete(@RequestBody RegisterAthleteDTO data){
        String subject = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails users = usersRepository.findByLogin(subject);
        Coach coach = coachRepository.findByUsers((Users) users);

        Athlete athlete = coachService.registerAthlete(data, coach);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Athlete", athlete.getName(), "category", athlete.getCategory(), "Coach", athlete.getCoach().getName()));
    }

    @PostMapping("/criar-competicao")
    public ResponseEntity<?> createCompetition(@RequestBody CreateCompetitionDTO data){
        ResponseCompetitionDTO response = competitionService.createCompetition(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/atualizar-parciais")
    public ResponseEntity<?> updatePartial(@RequestBody List<UpdatePartialDTO> data){
        partialService.updatePartial(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/meus-atletas")
    public ResponseEntity<?> getAllAthletes(){
        String subject = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails users = usersRepository.findByLogin(subject);
        Coach coach = coachRepository.findByUsers((Users) users);
        List<Athlete> athletes = athleteRepository.findByCoach(coach);
        if (athletes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(athletes);

    }

}
