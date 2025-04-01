package com.appointment.client.dtos;

import com.appointment.commons.dtos.response.StatusResponse;
import lombok.*;

import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConfigEmployeeResponse {
   private StoreEmployeeResponse storeEmployee;
   private Long defaultStatusAppointmentId;
   private Integer dayOfWeek;
   private LocalTime startTime;
   private LocalTime endTime;
   private LocalTime startTimeBreak;
   private LocalTime endTimeBreak;
   private Integer intervalInMinutes;
   private Integer appointmentsPerClient;
   private StatusResponse status;
}
