package com.swimtracker.swimtracker.repository;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.partial.Partial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PartialRepository extends JpaRepository<Partial, Long> {

    List<Partial> findByAthleteIn(List<Athlete> athletes);
}
