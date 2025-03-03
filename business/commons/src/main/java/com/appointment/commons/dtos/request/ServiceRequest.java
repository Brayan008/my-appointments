package com.appointment.commons.dtos.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceRequest implements Serializable {
    @NotBlank
    @Size(min = 5, max = 255)
    private String name;
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 12, fraction = 2, message = "El precio no debe tener más de 10 dígitos enteros y 2 decimales")
    private BigDecimal price;
    @NotNull
    private Long storeId;
}
