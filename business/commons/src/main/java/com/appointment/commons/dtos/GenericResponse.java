package com.appointment.commons.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse {
    @Schema(description = "A brief, human-readable message about the response", name = "title", required = true,
        example = "The appointment was created success")
    private String title;
    @Schema(description = "A human-readable explanation of the response", name = "detail", required = true,
        example = "Remember you have to arrive with anticipation")
    private String detail;
}
