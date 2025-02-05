package com.appointment.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    ENABLED(1L),
    DISABLED(2L);
    private final Long id;
}
