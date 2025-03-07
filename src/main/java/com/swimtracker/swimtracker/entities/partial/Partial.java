package com.swimtracker.swimtracker.entities.partial;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.proof.Proof;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Table(name = "partial")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "proof_id", referencedColumnName = "id")
    private Proof proof;

    @ManyToMany
    @JoinColumn(name = "athlete_id", referencedColumnName = "id")
    private Athlete athlete;

    private Integer partial_number;
    private Float time;
    private Float frequency;


}
