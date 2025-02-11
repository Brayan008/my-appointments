package com.appointment.commons.enums;

import lombok.Getter;

@Getter
public enum Memberships {
    ENABLED("PREMIUM", 1L),
    DISABLED("BASIC", 2L);

    private final String name;
    private final Long code;

    Memberships(String name, Long code) {
        this.name = name;
        this.code = code;
    }

    public static Long getCodeByName(String name) {
        for (Memberships memberships : Memberships.values()) {
            if (memberships.getName().equalsIgnoreCase(name)) {
                return memberships.getCode();
            }
        }
        return DISABLED.getCode();
    }
}
