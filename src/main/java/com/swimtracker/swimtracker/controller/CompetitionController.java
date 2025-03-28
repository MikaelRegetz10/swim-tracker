package com.swimtracker.swimtracker.controller;

import com.swimtracker.swimtracker.entities.competition.Competition;
import com.swimtracker.swimtracker.entities.competition.ResponseCompetitionDTO;
import com.swimtracker.swimtracker.exceptions.NotFoundException;
import com.swimtracker.swimtracker.repository.CompetitonRepository;
import com.swimtracker.swimtracker.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/competicao")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private CompetitonRepository competitonRepository;

    @GetMapping("/{name}")
    public ResponseEntity<ResponseCompetitionDTO> getCompetitions(@PathVariable String name) {
        Competition competition = competitonRepository.findByName(name).orElseThrow(()-> new NotFoundException(name));
        ResponseCompetitionDTO responseCompetitionDTO = competitionService.getCompetition(competition);
        return ResponseEntity.status(HttpStatus.OK).body(responseCompetitionDTO);
    }
}
