package com.swimtracker.swimtracker.entities.coach;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swimtracker.swimtracker.entities.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "coach")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coach implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;
    private String name;
    private String team;





}
