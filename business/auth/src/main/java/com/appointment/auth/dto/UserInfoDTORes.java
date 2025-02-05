package com.appointment.auth.dto;


import com.appointment.auth.entities.Role;
import com.appointment.auth.entities.Status;
import lombok.*;

@Getter
@Setter
@Builder
@Data
public class UserInfoDTORes {
    private Long idUser;
    private Long roleId;
    private Long statusId;
    private UserAuthResDTO userAuth0;
}
