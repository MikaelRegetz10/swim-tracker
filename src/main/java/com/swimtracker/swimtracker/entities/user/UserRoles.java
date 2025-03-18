package com.swimtracker.swimtracker.entities.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRoles {
    TECNICO("tecnico"),
    ADM("adm"),
    ATLETA("atleta");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }

    @JsonCreator
    public static UserRoles fromString(String role) {
        for (UserRoles userRole : UserRoles.values()) {
            if (userRole.getRole().equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + role);
    }
}
