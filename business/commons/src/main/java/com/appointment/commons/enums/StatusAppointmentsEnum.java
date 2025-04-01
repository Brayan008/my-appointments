package com.appointment.commons.enums;

import lombok.Getter;

@Getter
public enum StatusAppointmentsEnum {

   PENDING("PENDING", 1L),
   ACCEPTED("ACCEPTED", 2L),
   IN_PROGRESS("IN_PROGRESS", 3L),
   CANCELED("CANCELED", 4L),
   COMPLETED("COMPLETED", 5L),
   NOPAID("NOPAID", 6L),
   AVAILABLE("AVAILABLE", 7L);

    private final String name;
    private final Long code;

    StatusAppointmentsEnum(String name, Long id) {
        this.name = name;
        this.code = id;
    }

}
