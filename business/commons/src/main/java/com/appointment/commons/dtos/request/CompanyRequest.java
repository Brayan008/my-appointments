package com.appointment.commons.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyRequest implements Serializable {
    @NotBlank
    @Size(min = 5, max = 300)
    private String name;
    @NotBlank
    private String logo;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "El número de teléfono debe tener 10 dígitos sin lada")
    private String phoneNumber;
    private String instagramUrl;
    private String facebookUrl;
    @NotNull
    private Long membershipId;
    @NotNull
    private Long statusId;
}
