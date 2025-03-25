package com.swimtracker.swimtracker.entities.user;

public record RegisterUserDTO(String login, String password, UserRoles role) {
    @Override
    public String toString() {
        return "RegisterUserDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
