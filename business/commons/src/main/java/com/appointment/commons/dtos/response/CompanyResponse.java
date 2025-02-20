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
public class CompanyResponse implements Serializable {
    private Long companyId;
    private String name;
    private String logo;
    private String phoneNumber;
    private String instagramUrl;
    private String facebookUrl;
    private MembershipResponse membership;
    private StatusResponse status;
    private LocalDateTime createdAt;
}
