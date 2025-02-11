package com.appointment.commons.enums;

import lombok.Getter;

@Getter
public enum Status {
    ENABLED("ENABLED", 1L),
    DISABLED("DISABLED", 2L),
    OWNER_PENDING_APPROVE("OWNER_PENDING_APPROVE", 3L);

    private final String name;
    private final Long code;

    Status(String name, Long code) {
        this.name = name;
        this.code = code;
    }

    public static Long getCodeByName(String name) {
        for (Status status : Status.values()) {
            if (status.getName().equalsIgnoreCase(name)) {
                return status.getCode();
            }
        }
        return DISABLED.getCode();
    }
}
