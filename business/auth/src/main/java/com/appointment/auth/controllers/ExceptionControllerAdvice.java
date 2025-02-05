package com.appointment.auth.controllers;

import com.appointment.auth.dto.GenericErrorResponse;
import com.appointment.auth.exceptions.BadRequestException;
import com.appointment.auth.exceptions.CustomGenericException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
@Hidden
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex){
        log.error("BadRequestException {}", ex.getMessage());
        return new ResponseEntity<>(this.genericErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), ex.getDescription()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        log.error("Exception {}", ex.getMessage());
        return new ResponseEntity<>(this.genericErrorResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "Error generico", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CustomGenericException.class)
    public ResponseEntity<Object> handleGenericException(CustomGenericException ex){
        log.error("GenericException {}", ex.getMessage());
        return new ResponseEntity<>(this.genericErrorResponse(ex.getCode(), ex.getMessage(), ex.getDescription()), ex.getHttpStatus());
    }


    private Object genericErrorResponse(String code, String message, String description){
        return GenericErrorResponse
            .builder()
            .code(code)
            .message(message)
            .description(description)
            .build();
    }

}
