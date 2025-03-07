package com.swimtracker.swimtracker.repository;

import com.swimtracker.swimtracker.entities.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompetitonRepository extends JpaRepository<Competition, Long> {
}
