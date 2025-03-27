package com.swimtracker.swimtracker.entities.partial;

import java.util.Map;

public record PartialResponseDTO(String nameAthlete, String proof, Map<Integer, Float> partial) {

}


