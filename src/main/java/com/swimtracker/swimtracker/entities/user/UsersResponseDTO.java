package com.swimtracker.swimtracker.entities.user;

public record UsersResponseDTO(Long id, String login, UserRoles type) {

    public UsersResponseDTO(Users user) {
        this(user.getId(), user.getLogin(), user.getRoles());
    }
}
