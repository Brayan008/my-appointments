package com.appointment.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StoreEmployeeResponse {
   private Long storeEmployeeId;
   private UserResponse user;
   private StoreResponse store;
   private Long statusId;
}
