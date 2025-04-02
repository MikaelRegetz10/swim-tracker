package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.partial.Partial;
import com.swimtracker.swimtracker.entities.partial.PartialDTO;
import com.swimtracker.swimtracker.entities.partial.PartialResponseDTO;
import com.swimtracker.swimtracker.entities.partial.UpdatePartialDTO;
import com.swimtracker.swimtracker.entities.proof.Proof;
import com.swimtracker.swimtracker.exceptions.NotFoundException;
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

    protected List<Partial> processPartials(Proof proof, Map<Integer, List<Long>> series) {
        List<Partial> partials = new ArrayList<>();

        for (Map.Entry<Integer, List<Long>> entry : series.entrySet()) {
            partials.addAll(generatePartials(proof, entry.getValue(), entry.getKey()));
        }

        return partials;
    }

    private List<PartialResponseDTO> generateResponseDTO(Map<String, Map<String, Map<String, List<PartialDTO>>>> groupedPartials) {
        List<PartialResponseDTO> responseDTOs = new ArrayList<>();

        for (var competitionEntry : groupedPartials.entrySet()) {
            String competitionName = competitionEntry.getKey();
            for (var athleteEntry : competitionEntry.getValue().entrySet()) {
                String athleteName = athleteEntry.getKey();
                for (var proofEntry : athleteEntry.getValue().entrySet()) {
                    responseDTOs.add(new PartialResponseDTO(competitionName, athleteName, proofEntry.getKey(), proofEntry.getValue()));
                }
            }
        }

        return responseDTOs;
    }

    public List<PartialResponseDTO> getPartialByAthletes(List<Athlete> athletes) {
        List<Partial> partials = partialRepository.findByAthleteIn(athletes);

        Map<String, Map<String, Map<String, List<PartialDTO>>>> groupedPartials = new HashMap<>();

        for (Partial partial : partials) {
            String competitionName = partial.getProof().getCompetition().getName();
            String athleteName = partial.getAthlete().getName();
            String proof = partial.getProof().getDistance() + " " + partial.getProof().getStyleType();
            int meters = partial.getPartialNumber() * partial.getProof().getCompetition().getPoolType();
            Float time = partial.getTime();

            groupedPartials
                    .computeIfAbsent(competitionName, k -> new HashMap<>())
                    .computeIfAbsent(athleteName, k -> new HashMap<>())
                    .computeIfAbsent(proof, k -> new ArrayList<>())
                    .add(new PartialDTO(meters, time));
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
            Partial partial = partialRepository.findById(partialDTO.partialId()).orElseThrow(() -> new NotFoundException("Parcial"));
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
