package com.swimtracker.swimtracker.entities.user;

public record RegisterDTO(String login, String password, UserRoles role) {
}
