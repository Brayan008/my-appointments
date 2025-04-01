package com.appointment.commons.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeAppointmentsAvailabilityResponse {

   private Integer dayOfWeek;
   private List<AppointmentDetailEmployeeRes> appointments;


}
