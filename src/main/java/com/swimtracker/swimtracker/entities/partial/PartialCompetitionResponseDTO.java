package com.swimtracker.swimtracker.entities.partial;

import com.swimtracker.swimtracker.entities.athlete.ResponseAthleteDTO;
import com.swimtracker.swimtracker.entities.proof.ProofDetailDTO;

public record PartialCompetitionResponseDTO(Long id,
                                            int partialNumber,
                                            Float time,
                                            Float frequency,
                                            ResponseAthleteDTO athlete
                                            ) {
}
