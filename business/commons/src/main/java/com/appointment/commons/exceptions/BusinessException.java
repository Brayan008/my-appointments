package com.appointment.commons.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BusinessException extends Exception {
    private Long id;
    private String code;
    private String detail;
    private HttpStatus httpStatus;

    public BusinessException(Long id, String code, String message, HttpStatus httpStatus) {
        super(message);
        this.id = id;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public BusinessException(String code, String message, String detail, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.detail = detail;
        this.httpStatus = httpStatus;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
