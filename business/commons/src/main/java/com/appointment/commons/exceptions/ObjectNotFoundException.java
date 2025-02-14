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
    private String objectNotFoundName;
    private HttpStatus httpStatus;
    private Throwable cause;

    public ObjectNotFoundException(int code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
        this.cause = null;
    }

    public ObjectNotFoundException(String objectNotFoundName, String message,
                                   Throwable cause) {
        super(message, cause);
        this.objectNotFoundName = objectNotFoundName;
        this.cause = cause;
    }

    @Override
    public String getMessage(){
        String message = super.getMessage() == null ? "": super.getMessage();
        return message
            .concat("( object not found: ")
            .concat(this.objectNotFoundName)
            .concat(")");
    }
}
