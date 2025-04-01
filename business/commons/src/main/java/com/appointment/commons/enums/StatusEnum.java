package com.appointment.commons.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    ENABLED("ENABLED", 1L),
    DISABLED("DISABLED", 2L),
    OWNER_PENDING_APPROVE("OWNER_PENDING_APPROVE", 3L);

    private final String name;
    private final Long code;

    StatusEnum(String name, Long code) {
        this.name = name;
        this.code = code;
    }

    public static Long getCodeByName(String name) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getName().equalsIgnoreCase(name)) {
                return statusEnum.getCode();
            }
        }
        return DISABLED.getCode();
    }
}
