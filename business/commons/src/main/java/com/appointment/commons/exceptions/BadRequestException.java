package com.appointment.commons.exceptions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BadRequestException extends Exception {

    private String detail;

    public BadRequestException(String message, String detail){
        super(message);
        this.detail = detail;
    }
}
