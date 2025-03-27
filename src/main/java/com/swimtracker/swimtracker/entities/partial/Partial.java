package com.swimtracker.swimtracker.entities.partial;

import com.swimtracker.swimtracker.entities.athlete.Athlete;
import com.swimtracker.swimtracker.entities.proof.Proof;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "partial")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partial implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proof_id")
    private Proof proof;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    private Integer partialNumber;
    private Float time;
    private Float frequency;
    private Integer serie;
    private Integer finish;


    public Partial(Proof proof, Athlete athlete, Integer partialNumber, Float time, Float frequency, Integer serie) {
        this.proof = proof;
        this.athlete = athlete;
        this.partialNumber = partialNumber;
        this.time = time;
        this.frequency = frequency;
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Partial{" +
                "athlete=" + athlete +
                ", id=" + id +
                ", proof=" + proof +
                ", partialNumber=" + partialNumber +
                ", time=" + time +
                ", frequency=" + frequency +
                ", serie=" + serie +
                '}';
    }
}
