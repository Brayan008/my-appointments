package com.appointment.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CompanyResponse {
   private String name;
   private String logo;
   private String phoneNumber;
   private String instagramUrl;
   private String facebookUrl;
}
