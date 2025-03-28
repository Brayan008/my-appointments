package com.appointment.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageableClientAppointmentResponse {

   private List<ClientAppointmentResponse> content;
   private int totalElements;
   private int totalPages;
   private int number;
   private int size;

}
