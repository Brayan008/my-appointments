package com.appointment.owner.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse implements Serializable {
    private Long serviceId;
    private String name;
    private BigDecimal price;
    private Long storeId;
    private StoreResponse store;
    private LocalDateTime createdAt;
}
