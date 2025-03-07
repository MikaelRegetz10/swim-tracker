package com.swimtracker.swimtracker.entities.competition;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Table(name = "competition")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Competition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;
    private String name;
    private PoolType pool_type;

}
