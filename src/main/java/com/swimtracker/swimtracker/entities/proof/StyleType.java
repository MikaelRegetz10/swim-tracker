package com.swimtracker.swimtracker.entities.proof;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StyleType {
    CRAWL("crawl"),
    BORBOLETA("borboleta"),
    PEITO("peito"),
    COSTAS("costas");

    private String type;

    StyleType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getRole() {
        return type;
    }
}
