package com.appointment.commons.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDateRequest {

    private LocalDateTime userDate;

    private Long statusDateId;

    private Long serviceId;

    private Double totalPaid;

    private String employeeEmail;

    private String clientEmail;

}
