package com.swimtracker.swimtracker.entities.coach;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swimtracker.swimtracker.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Table(name = "coach")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coach implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String name;
    private String team;





}
