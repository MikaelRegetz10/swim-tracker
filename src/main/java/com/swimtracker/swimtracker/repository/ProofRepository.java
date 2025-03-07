package com.swimtracker.swimtracker.repository;

import com.swimtracker.swimtracker.entities.proof.Proof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProofRepository extends JpaRepository<Proof, Long> {
}
