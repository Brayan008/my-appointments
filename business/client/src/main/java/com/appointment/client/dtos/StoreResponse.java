package com.appointment.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class StoreResponse {
   private String name;
   private String address;
   private String description;
   private BigDecimal latitude;
   private BigDecimal longitude;
   private CompanyResponse company;
   private Long statusId;
}
