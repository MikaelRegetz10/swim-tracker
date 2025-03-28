package com.swimtracker.swimtracker.entities.partial;

import java.util.Map;

public record PartialResponseDTO(String competitionName, String athleteName, String proof, Map<Integer, Float> partials) {
}



