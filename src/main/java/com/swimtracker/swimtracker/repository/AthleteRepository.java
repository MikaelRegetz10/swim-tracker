package com.swimtracker.swimtracker.repository;


import com.swimtracker.swimtracker.entities.athlete.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Optional<Athlete> findById(Long id);
}
