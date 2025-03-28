package com.swimtracker.swimtracker.entities.competition;

import com.swimtracker.swimtracker.entities.proof.ProofResponseDTO;

import java.util.List;
import java.util.Map;

public record ResponseCompetitionDTO(String competitionName,
                                     Map<String, List<ProofResponseDTO>> proofs) {}