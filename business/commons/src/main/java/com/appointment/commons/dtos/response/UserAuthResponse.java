package com.appointment.commons.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthResponse {
    private String sub;
    private String nickname;
    private String name;
    private String picture;
    private String email;
    private String role;
}
