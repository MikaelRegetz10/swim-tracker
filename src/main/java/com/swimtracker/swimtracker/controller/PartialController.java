package com.swimtracker.swimtracker.controller;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.partial.Partial;
import com.swimtracker.swimtracker.entities.partial.PartialResponseDTO;
import com.swimtracker.swimtracker.repository.AthleteRepository;
import com.swimtracker.swimtracker.repository.PartialRepository;
import com.swimtracker.swimtracker.services.PartialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parciais")
public class PartialController {
    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private PartialService partialService;

    @GetMapping
    public ResponseEntity<List<PartialResponseDTO>> findPartialByAthlete(@RequestParam String atleta) {
        List<Athlete> athletes = athleteRepository.findByNameContainingIgnoreCase(atleta);

        List<PartialResponseDTO> response = partialService.getPartialByAthletes(athletes);

        return ResponseEntity.ok().body(response);
    }


}
