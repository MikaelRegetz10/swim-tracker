package com.swimtracker.swimtracker.entities.athlete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swimtracker.swimtracker.entities.coach.Coach;
import com.swimtracker.swimtracker.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Table(name = "athlete")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Athlete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;
    private String category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private Coach coach;


}
