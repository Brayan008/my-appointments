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
public class CompanyRequest implements Serializable {
    private String name;
    private String logo;
    private String phoneNumber;
    private String instagramUrl;
    private String facebookUrl;
    private Long membershipId;
    private Long statusId;
}
