package com.appointment.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StoreResponse {
   private String name;
   private String address;
   private String description;
   private String coordinates;
   private CompanyResponse company;
   private Long statusId;
}
