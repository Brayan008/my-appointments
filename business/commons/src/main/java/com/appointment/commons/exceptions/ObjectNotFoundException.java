package com.appointment.commons.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ObjectNotFoundException extends RuntimeException {
    private Long id;
    private int code;
    private HttpStatus httpStatus;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(int code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
