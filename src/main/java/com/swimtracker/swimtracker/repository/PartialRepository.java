package com.swimtracker.swimtracker.repository;

import com.swimtracker.swimtracker.entities.partial.Partial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PartialRepository extends JpaRepository<Partial, Long> {
}
