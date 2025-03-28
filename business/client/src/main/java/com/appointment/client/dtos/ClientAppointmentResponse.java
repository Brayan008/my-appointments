package com.appointment.client.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class ClientAppointmentResponse {
   private String userAppointment;
   private Long statusAppointmentId;
   private ServiceResponse service;
   private Double totalPaid;
   private StoreEmployeeResponse storeEmployee;
   private String createdAt;
}
