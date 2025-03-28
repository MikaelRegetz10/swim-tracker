package com.swimtracker.swimtracker.entities.proof;

public record ProofDetailDTO(
        Long id,
        int distance,
        StyleType styleType,
        Integer proofOrder
) {}

