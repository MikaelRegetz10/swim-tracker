package com.swimtracker.swimtracker.entities.athlete;

public record ResponseAthleteDTO(Long id, String name, String category) {
    public ResponseAthleteDTO (Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
