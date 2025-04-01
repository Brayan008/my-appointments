package com.appointment.client.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ClientAppointmentResponse {
   private LocalDateTime userAppointment;
   private Long statusAppointmentId;
   private ServiceResponse service;
   private Double totalPaid;
   private StoreEmployeeResponse storeEmployee;
   private String createdAt;
}
