package com.swimtracker.swimtracker.entities.proof;

import com.swimtracker.swimtracker.entities.partial.PartialCompetitionResponseDTO;

public record ProofResponseDTO(
        String proofName,
        int series,
        PartialCompetitionResponseDTO partial) {}
