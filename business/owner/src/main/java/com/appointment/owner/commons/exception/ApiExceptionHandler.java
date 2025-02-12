package com.appointment.owner.commons.exception;

import java.net.UnknownHostException;

import com.appointment.commons.dtos.StandardizedApiExceptionResponse;
import com.appointment.commons.exceptions.BusinessException;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Standard http communication have five levels of response codes
 * 100-level (Informational) — Server acknowledges a request, it means that request was received and understood, it is transient response , alert client for awaiting response
 * 200-level (Success) — Server completed the request as expected
 * 300-level (Redirection) — Client needs to perform further actions to complete the request
 * 400-level (Client error) — Client sent an invalid request
 * 500-level (Server error) — Server failed to fulfill a valid request due to an error with server
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleUnknownHostException(UnknownHostException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de conexion",
            "error-1024", ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleBusinessException(BusinessException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de validacion",
            ex.getCode(), ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleObjectNotFoundException(BusinessException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de validacion",
            ex.getCode(), ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }
}
