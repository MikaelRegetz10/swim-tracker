package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.entities.competition.Competition;
import com.swimtracker.swimtracker.entities.competition.CreateCompetitionDTO;
import com.swimtracker.swimtracker.entities.partial.Partial;
import com.swimtracker.swimtracker.entities.proof.CreateProofDTO;
import com.swimtracker.swimtracker.entities.proof.Proof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProofService {

    @Autowired
    private PartialService partialService;

    protected List<Proof> processProof(CreateCompetitionDTO dto, Competition competition){
        List<Proof> proofs = new ArrayList<>();

        for (CreateProofDTO proofDTO : dto.proofs()) {
            Proof proof = competition.getProofs().stream()
                    .filter(p -> p.getDistance().equals(proofDTO.distance()) &&
                            p.getStyleType().equals(proofDTO.styleType()))
                    .findFirst()
                    .orElseGet(() -> new Proof(proofDTO.distance(), proofDTO.styleType(), proofDTO.proofOrder(), competition));

            List<Partial> partials = partialService.processPartials(proof, proofDTO.series());
            proof.getPartials().addAll(partials);
            proofs.add(proof);
        }

        return proofs;
    }
}
