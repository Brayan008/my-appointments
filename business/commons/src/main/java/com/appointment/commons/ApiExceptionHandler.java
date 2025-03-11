package com.appointment.commons;

import com.appointment.commons.dtos.StandardizedApiExceptionResponse;
import com.appointment.commons.exceptions.BadRequestException;
import com.appointment.commons.exceptions.BusinessException;
import com.appointment.commons.exceptions.NotFoundException;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;

/**
 * Standard http communication have five levels of response codes
 * 100-level (Informational) — Server acknowledges a request, it means that request was received and understood, it is transient response , alert client for awaiting response
 * 200-level (Success) — Server completed the request as expected
 * 300-level (Redirection) — Client needs to perform further actions to complete the request
 * 400-level (Client error) — Client sent an invalid request
 * 500-level (Server error) — Server failed to fulfill a valid request due to an error with server
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleUnknownHostException(UnknownHostException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de conexion",
            "error-1024", ex.getMessage());
        log.info("UnknownHostException {} ", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleBusinessException(BusinessException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse(ex.getMessage(),
            ex.getCode(), ex.getDetail());
        log.info("BusinessException {} {}", ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleObjectNotFoundException(ObjectNotFoundException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de validacion",
            String.valueOf(ex.getCode()), ex.getMessage());
        log.info("ObjectNotFoundException {} {}", ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex){
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse(ex.getMessage(),
            String.valueOf(HttpStatus.BAD_REQUEST), ex.getDetail());
        log.info("BadRequestException {} {}", ex.getMessage(), ex.getDetail());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        log.error("Exception {}", ex.getMessage());
        ex.printStackTrace();
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de generico",
            String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
