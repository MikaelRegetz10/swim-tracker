package com.swimtracker.swimtracker.repository;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CompetitonRepository extends JpaRepository<Competition, Long> {

    List<Competition> findByNameContainingIgnoreCase(String name);
    Optional<Competition> findByName(String name);
}
