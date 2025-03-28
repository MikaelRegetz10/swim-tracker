package com.swimtracker.swimtracker.repository;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.partial.Partial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PartialRepository extends JpaRepository<Partial, Long> {

    List<Partial> findByAthleteIn(List<Athlete> athletes);

    @Query("SELECT p FROM Partial p " +
            "JOIN FETCH p.proof pr " +
            "JOIN FETCH pr.competition c " +
            "WHERE c.id = :competitionId " +
            "ORDER BY pr.proofOrder ASC, p.serie ASC")
    List<Partial> findByCompetition(@Param("competitionId") Long competitionId);
}
