package com.swimtracker.swimtracker.controller;

import com.swimtracker.swimtracker.entities.competition.Competition;
import com.swimtracker.swimtracker.entities.competition.ResponseCompetitionDTO;
import com.swimtracker.swimtracker.exceptions.NotFoundException;
import com.swimtracker.swimtracker.repository.CompetitonRepository;
import com.swimtracker.swimtracker.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competicao")
@CrossOrigin
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private CompetitonRepository competitonRepository;

    @GetMapping("/{name}")
    public ResponseEntity<List<ResponseCompetitionDTO>> getCompetitions(@PathVariable String name) {
        List<Competition> competitions = competitonRepository.findByNameContainingIgnoreCase(name);

        if (competitions.isEmpty()) {
            throw new NotFoundException("Competicao " + name);
        }

        List<ResponseCompetitionDTO> responseCompetitionDTO = competitionService.getCompetitions(competitions);
        return ResponseEntity.status(HttpStatus.OK).body(responseCompetitionDTO);
    }
}
