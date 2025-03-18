package com.swimtracker.swimtracker.entities.proof;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swimtracker.swimtracker.entities.competition.Competition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "proof")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proof implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "competiton_id")
    private Competition competition;

    private Integer distance;

    private StyleType styleType;

}
