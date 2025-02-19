package com.appointment.owner.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest implements Serializable {
    private Long userId;
    private Long storeId;
    private Long statusId;
}
