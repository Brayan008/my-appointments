package com.appointment.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthResDTO {
    private String sub;
    private String nickname;
    private String name;
    private String picture;
    private String email;
    private String role;
}
