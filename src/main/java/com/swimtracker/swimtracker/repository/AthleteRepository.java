package com.swimtracker.swimtracker.repository;


import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.coach.Coach;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Optional<Athlete> findById(Long id);
    List<Athlete> findByNameContainingIgnoreCase(String name);
    List<Athlete> findByCoachOrderByNameAsc(Coach coach);
}
