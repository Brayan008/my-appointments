package com.appointment.commons.dtos.request;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Long userId;
    @NotNull
    private Long storeId;
    @NotNull
    private Long statusId;
}
