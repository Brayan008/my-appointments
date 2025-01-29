package com.appointment.auth.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomGenericException extends RuntimeException{
    private String description;
    private String code;
    private HttpStatus httpStatus;

    public CustomGenericException(String message, String description, String code, HttpStatus httpStatus){
        super(message);
        this.description = description;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
