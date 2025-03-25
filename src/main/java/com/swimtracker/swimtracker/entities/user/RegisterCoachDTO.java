package com.swimtracker.swimtracker.entities.user;

public record RegisterCoachDTO(String login, String password, UserRoles role, String team, String name) {
}
