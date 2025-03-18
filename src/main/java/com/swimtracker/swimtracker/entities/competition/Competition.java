package com.swimtracker.swimtracker.entities.competition;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Table(name = "competition")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Competition implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;
    private String name;
    private Integer poolType;

}
