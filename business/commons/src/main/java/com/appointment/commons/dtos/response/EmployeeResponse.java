package com.appointment.commons.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse implements Serializable {
    private Long employeeId;
    private Long userId;
    private Long storeId;
    private UserResponse user;
    private StoreResponse store;
    private StatusResponse status;
}
