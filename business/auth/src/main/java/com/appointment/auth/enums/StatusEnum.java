package com.appointment.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    ENABLED("ENABLED"),
    DISABLED("DISABLED");
    private final String value;
}
