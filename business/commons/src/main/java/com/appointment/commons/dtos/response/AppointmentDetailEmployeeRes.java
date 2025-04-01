package com.appointment.commons.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDetailEmployeeRes {
   private LocalDateTime userAppointment;
   private Long statusAppointmentId;
}
