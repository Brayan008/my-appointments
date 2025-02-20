package com.appointment.commons.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreRequest implements Serializable {
    private String name;
    private String address;
    private String description;
    private String coordinates;
    private Long statusId;
    private Long companyId;
}
