package com.swimtracker.swimtracker.entities.proof;

import java.util.List;

public record CreateProofDTO(Integer distance, StyleType styleType, Integer proofOrder, List<SerieDTO> series) {
}
