package com.swimtracker.swimtracker.entities.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swimtracker.swimtracker.entities.proof.Proof;
import com.swimtracker.swimtracker.entities.proof.StyleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "competition")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Competition implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Competition(String competitionName, LocalDate date, Integer poolType) {
        this.name = competitionName;
        this.date = date;
        this.poolType = poolType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    private String name;

    private Integer poolType;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proof> proofs = new ArrayList<>();

    @Override
    public String toString() {
        return "Competition{" +
                "date=" + date +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", poolType=" + poolType +
                ", proofs=" + proofs +
                '}';
    }
}
