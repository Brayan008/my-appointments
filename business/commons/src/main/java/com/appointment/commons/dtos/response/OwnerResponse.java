package com.appointment.commons.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerResponse implements Serializable {
    private Long ownerId;
    private Long userId;
    private Long companyId;
    private UserResponse user;
    private CompanyResponse company;
    private LocalDateTime createdAt;
}
