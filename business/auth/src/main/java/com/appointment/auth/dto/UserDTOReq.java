package com.appointment.auth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTOReq {
    private String userId;
    private String email;
    private String securityKey;
}
