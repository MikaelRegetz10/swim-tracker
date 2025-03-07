package com.swimtracker.swimtracker.repository;


import com.swimtracker.swimtracker.entities.athlete.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}
