package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.entities.competition.Competition;
import com.swimtracker.swimtracker.entities.competition.CreateCompetitionDTO;
import com.swimtracker.swimtracker.entities.competition.ResponseCompetitionDTO;
import com.swimtracker.swimtracker.entities.partial.Partial;
import com.swimtracker.swimtracker.entities.proof.CreateProofDTO;
import com.swimtracker.swimtracker.entities.proof.Proof;
import com.swimtracker.swimtracker.entities.proof.StyleType;
import com.swimtracker.swimtracker.repository.AthleteRepository;
import com.swimtracker.swimtracker.repository.CompetitonRepository;
import com.swimtracker.swimtracker.repository.ProofRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CompetitionService {

    @Autowired
    private CompetitonRepository competitonRepository;

    @Autowired
    private PartialService partialService;

    private List<Proof> processProof(CreateCompetitionDTO dto, Competition competition){
        List<Proof> proofs = new ArrayList<>();

        for (CreateProofDTO proofDTO : dto.proofs()) {
            Proof proof = competition.getProofs().stream()
                    .filter(p -> p.getDistance().equals(proofDTO.distance()) &&
                            p.getStyleType().equals(proofDTO.styleType()))
                    .findFirst()
                    .orElseGet(() -> new Proof(proofDTO.distance(), proofDTO.styleType(), competition));

            List<Partial> partials = processPartials(proof, proofDTO.series());
            proof.getPartials().addAll(partials);
            proofs.add(proof);
        }

        return proofs;
    }

    private List<Partial> processPartials(Proof proof, Map<Integer, List<Long>> series) {
        List<Partial> partials = new ArrayList<>();

        for (Map.Entry<Integer, List<Long>> entry : series.entrySet()) {
            partials.addAll(partialService.generatePartials(proof, entry.getValue(), entry.getKey()));
        }

        return partials;
    }

    @Transactional
    public ResponseCompetitionDTO createCompetition(CreateCompetitionDTO createCompetitionDTO) {
        Competition competition = competitonRepository.findByName(createCompetitionDTO.name())
                .orElseGet(() -> competitonRepository.save(new Competition(createCompetitionDTO.name(), createCompetitionDTO.date(), createCompetitionDTO.poolType())));

        List<Proof> proofs = processProof(createCompetitionDTO, competition);

        competition.getProofs().addAll(proofs);

        competitonRepository.save(competition);

        List<StyleType> styleTypes = competition.getProofs().stream().map(Proof::getStyleType).toList();
        ResponseCompetitionDTO response = new ResponseCompetitionDTO(competition.getName(), competition.getPoolType(), competition.getDate(), styleTypes);
        return response;
    }
}
