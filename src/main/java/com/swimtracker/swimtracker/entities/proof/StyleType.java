package com.swimtracker.swimtracker.entities.proof;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StyleType {
    CRAWL("crawl"),
    BORBOLETA("borboleta"),
    PEITO("peito"),
    COSTAS("costas"),
    MEDLEY("medley");

    private String type;

    StyleType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getRole() {
        return type;
    }

    @JsonCreator
    public static StyleType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Valor nulo não é permitido");
        }
        for (StyleType style : values()) {
            if (style.type.equalsIgnoreCase(value)) {
                return style;
            }
        }
        throw new IllegalArgumentException("Estilo inválido: " + value);
    }
}
