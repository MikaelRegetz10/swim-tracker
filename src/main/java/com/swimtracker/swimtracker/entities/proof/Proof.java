package com.swimtracker.swimtracker.entities.proof;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swimtracker.swimtracker.entities.competition.Competition;
import com.swimtracker.swimtracker.entities.partial.Partial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private Integer distance;

    @Enumerated(EnumType.ORDINAL)
    private StyleType styleType;

    @OneToMany(mappedBy = "proof", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partial> partials = new ArrayList<>();

    public Proof(Integer distance, StyleType styleType, Competition competition) {
        this.distance = distance;
        this.styleType = styleType;
        this.competition = competition;
    }

}
