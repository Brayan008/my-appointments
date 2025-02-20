package com.appointment.commons.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NotFoundException extends Exception {
    private String message;
    private String code;
    private String detail;
    private HttpStatus httpStatus;

    public NotFoundException(String code, String message, String detail, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
