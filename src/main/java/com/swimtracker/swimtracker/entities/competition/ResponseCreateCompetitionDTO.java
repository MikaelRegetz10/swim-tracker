package com.swimtracker.swimtracker.entities.competition;

import com.swimtracker.swimtracker.entities.proof.StyleType;

import java.time.LocalDate;
import java.util.List;

public record ResponseCreateCompetitionDTO(String name, Integer distance, LocalDate date, List<StyleType> proofsList) {

    public ResponseCreateCompetitionDTO(String name, Integer distance, LocalDate date, List<StyleType> proofsList) {
        this.name = name;
        this.distance = distance;
        this.date = date;
        this.proofsList = proofsList;
    }
}
