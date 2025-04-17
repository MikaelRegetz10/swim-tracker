package com.swimtracker.swimtracker.entities.proof;

import java.util.List;

public record SerieDTO(Integer serieNumber, List<Long> athletes) {
}
