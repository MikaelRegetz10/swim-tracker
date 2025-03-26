package com.swimtracker.swimtracker.entities.coach;

import com.swimtracker.swimtracker.entities.user.UserRoles;

public record RegisterCoachDTO(String login, String password, UserRoles role, String team, String name) {
}
