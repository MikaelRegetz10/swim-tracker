package com.swimtracker.swimtracker.entities.partial;

import java.util.List;

public record PartialResponseDTO(String competitionName, String athleteName, String proof, List<PartialDTO> partials) {
}



