package com.appointment.auth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GenericErrorResponse {
    private String code;
    private String message;
    private String description;
}
