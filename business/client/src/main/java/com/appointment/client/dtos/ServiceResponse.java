package com.appointment.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ServiceResponse {
   private String name;
   private Double price;
   private StoreResponse store;
}
