package com.appointment.client.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class NewAppointmentReq {

    private LocalDateTime userAppointment;
    private Long serviceId;
    private Long storeEmployeeId;

}
