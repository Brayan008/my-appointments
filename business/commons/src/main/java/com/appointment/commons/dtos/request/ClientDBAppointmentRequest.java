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
public class ClientDBAppointmentRequest {
    private LocalDateTime userAppointment;
    private Long serviceId;
    private Long storeEmployeeId;
    private String clientEmail;

}
