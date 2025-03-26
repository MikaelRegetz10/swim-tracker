package com.swimtracker.swimtracker.entities.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swimtracker.swimtracker.entities.proof.CreateProofDTO;

import java.time.LocalDate;
import java.util.List;

public record CreateCompetitionDTO(
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
        LocalDate date,
        Integer poolType,
        List<CreateProofDTO> proofs) {
}
