package com.appointment.auth.dto;

import com.appointment.commons.dtos.response.UserAuthResponse;
import lombok.*;

@Getter
@Setter
@Builder
@Data
public class UserInfoDTORes {
    private Long idUser;
    private Long roleId;
    private Long statusId;
    private UserAuthResponse userAuth0;
}
