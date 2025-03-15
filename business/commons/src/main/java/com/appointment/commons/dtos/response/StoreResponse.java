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
public class StoreResponse implements Serializable {
    private Long storeId;
    private String name;
    private String address;
    private String description;
    private String coordinates;
    private Long statusId;
    private Long companyId;
    private StatusResponse status;
    private CompanyResponse company;
}
