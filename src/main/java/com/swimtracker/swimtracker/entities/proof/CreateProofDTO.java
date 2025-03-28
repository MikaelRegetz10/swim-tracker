package com.swimtracker.swimtracker.entities.proof;

import java.util.List;
import java.util.Map;

public record CreateProofDTO(Integer distance, StyleType styleType, Integer proofOrder, Map<Integer, List<Long>> series) {
}
