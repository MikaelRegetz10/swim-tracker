package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.partial.Partial;
import com.swimtracker.swimtracker.entities.partial.PartialResponseDTO;
import com.swimtracker.swimtracker.entities.partial.UpdatePartialDTO;
import com.swimtracker.swimtracker.entities.proof.Proof;
import com.swimtracker.swimtracker.exceptions.PartialNotFoundException;
import com.swimtracker.swimtracker.repository.AthleteRepository;
import com.swimtracker.swimtracker.repository.PartialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartialService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private PartialRepository partialRepository;

    private List<PartialResponseDTO> generateResponseDTO(Map<String, Map<String, Map<Integer, Float>>> groupedPartials) {
        List<PartialResponseDTO> responseDTOs = new ArrayList<>();
        for (var entry : groupedPartials.entrySet()) {
            String name = entry.getKey();
            for (var partialEntry : entry.getValue().entrySet()) {
                responseDTOs.add(new PartialResponseDTO(name, partialEntry.getKey(), partialEntry.getValue()));
            }
        }
        return responseDTOs;
    }

    public List<PartialResponseDTO> getPartialByAthletes(List<Athlete> athletes) {
        List<Partial> partials = partialRepository.findByAthleteIn(athletes);

        Map<String, Map<String, Map<Integer, Float>>> groupedPartials = new HashMap<>();

        for (Partial partial : partials) {
            String name = partial.getAthlete().getName();
            String proof = partial.getProof().getDistance() + " " + partial.getProof().getStyleType();

            groupedPartials
                    .computeIfAbsent(name, k -> new HashMap<>())
                    .computeIfAbsent(proof, k -> new HashMap<>())
                    .put(partial.getPartialNumber()*partial.getProof().getCompetition().getPoolType(), partial.getTime());
        }

        return generateResponseDTO(groupedPartials);
    }

    public List<Partial> generatePartials(Proof proof, List<Long> athletesId, Integer serie) {
        int numPartials = proof.getDistance() / 50;
        if (proof.getDistance() == 100 || proof.getDistance() == 50) {
            numPartials = proof.getDistance() / 25;
        }

        List<Partial> partials = new ArrayList<>();

        for (Long id : athletesId) {
            for (int i = 0; i < numPartials; i++) {
                Partial partial = new Partial(proof, athleteRepository.findById(id).orElseThrow(), i + 1, null, null, serie);
                partials.add(partial);
            }
        }
        return partials;
    }

    @Transactional
    public void updatePartial(List<UpdatePartialDTO> partialsDTO){
        for (UpdatePartialDTO partialDTO : partialsDTO) {
            Partial partial = partialRepository.findById(partialDTO.partialId()).orElseThrow(PartialNotFoundException::new);
            if (partialDTO.time() != null) {
                partial.setTime(partialDTO.time());
            }
            if (partialDTO.frequency() != null) {
                partial.setFrequency(partialDTO.frequency());
            }
            partialRepository.save(partial);
        }
    }
}
