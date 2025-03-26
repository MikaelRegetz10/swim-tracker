package com.swimtracker.swimtracker.services;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.partial.Partial;
import com.swimtracker.swimtracker.entities.proof.Proof;
import com.swimtracker.swimtracker.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartialService {

    @Autowired
    private AthleteRepository athleteRepository;


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
}
