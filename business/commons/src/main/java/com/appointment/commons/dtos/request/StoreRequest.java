package com.appointment.commons.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class StoreRequest implements Serializable {
    @NotBlank
    @Size(min = 5, max = 255)
    private String name;
    @NotBlank
    @Size(min = 5, max = 255)
    private String address;
    @NotBlank
    private String description;
    @NotBlank
    @Size(min = 5, max = 255)
    private String coordinates;
    @NotNull
    private Long statusId;
    @NotNull
    private Long companyId;
}
