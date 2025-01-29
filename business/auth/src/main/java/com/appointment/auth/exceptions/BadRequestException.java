package com.appointment.auth.exceptions;


import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{

    private String description;

    public BadRequestException(String message, String description){
        super(message);
        this.description = description;
    }
}
