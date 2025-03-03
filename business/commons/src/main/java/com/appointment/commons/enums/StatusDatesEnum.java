package com.appointment.commons.enums;

import lombok.Getter;

@Getter
public enum StatusDatesEnum {

    PENDING("PENDING", 1L),
    CANCELED("CANCELED", 2L),
    COMPLETED("COMPLETED", 3L),
    NOPAID("NOPAID", 4L);

    private final String name;
    private final Long code;

    StatusDatesEnum(String name, Long id) {
        this.name = name;
        this.code = id;
    }

}
