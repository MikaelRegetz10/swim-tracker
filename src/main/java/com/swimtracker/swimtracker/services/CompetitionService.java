package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.entities.athlete.ResponseAthleteDTO;
import com.swimtracker.swimtracker.entities.competition.Competition;
import com.swimtracker.swimtracker.entities.competition.CreateCompetitionDTO;
import com.swimtracker.swimtracker.entities.competition.ResponseCompetitionDTO;
import com.swimtracker.swimtracker.entities.competition.ResponseCreateCompetitionDTO;
import com.swimtracker.swimtracker.entities.partial.Partial;
import com.swimtracker.swimtracker.entities.partial.PartialCompetitionResponseDTO;
import com.swimtracker.swimtracker.entities.proof.Proof;
import com.swimtracker.swimtracker.entities.proof.ProofResponseDTO;
import com.swimtracker.swimtracker.entities.proof.StyleType;
import com.swimtracker.swimtracker.repository.CompetitonRepository;
import com.swimtracker.swimtracker.repository.PartialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CompetitionService {

    @Autowired
    private CompetitonRepository competitonRepository;

    @Autowired
    private ProofService proofService;

    @Autowired
    private PartialRepository partialRepository;

    @Transactional
    public ResponseCreateCompetitionDTO createCompetition(CreateCompetitionDTO createCompetitionDTO) {
        Competition competition = competitonRepository.findByName(createCompetitionDTO.name())
                .orElseGet(() -> competitonRepository.save(new Competition(createCompetitionDTO.name(), createCompetitionDTO.date(), createCompetitionDTO.poolType())));

        List<Proof> proofs = proofService.processProof(createCompetitionDTO, competition);

        competition.getProofs().addAll(proofs);

        competitonRepository.save(competition);

        List<StyleType> styleTypes = competition.getProofs().stream().map(Proof::getStyleType).toList();
        ResponseCreateCompetitionDTO response = new ResponseCreateCompetitionDTO(competition.getName(), competition.getPoolType(), competition.getDate(), styleTypes);
        return response;
    }

    private ProofResponseDTO createJsonResponse(String proofName, Partial partial) {
        return new ProofResponseDTO(
                proofName,
                partial.getSerie(),
                new PartialCompetitionResponseDTO(
                        partial.getId(),
                        partial.getPartialNumber(),
                        partial.getTime(),
                        partial.getFrequency(),
                        new ResponseAthleteDTO(
                                partial.getAthlete().getId(),
                                partial.getAthlete().getName(),
                                partial.getAthlete().getCategory()
                        )
                )
        );
    }

    public ResponseCompetitionDTO getCompetition(Competition competition) {

        List<Partial> partials = partialRepository.findByCompetition(competition.getId());

        Map<String, List<ProofResponseDTO>> proofsMap = new LinkedHashMap<>();

        for (Partial partial : partials) {
            String proofName = partial.getProof().getDistance() + " " + partial.getProof().getStyleType();
            ProofResponseDTO proofResponseDTO = createJsonResponse(proofName, partial);
            proofsMap.computeIfAbsent(proofName, k -> new ArrayList<>()).add(proofResponseDTO);
        }

        return new ResponseCompetitionDTO(competition.getName(), proofsMap);
    }

}
