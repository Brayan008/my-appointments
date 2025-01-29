package com.appointment.auth.service.impl;

import com.appointment.auth.dto.UserDTOReq;
import com.appointment.auth.dto.UserDTORes;
import com.appointment.auth.entities.Role;
import com.appointment.auth.entities.Status;
import com.appointment.auth.entities.User;
import com.appointment.auth.enums.StatusEnum;
import com.appointment.auth.exceptions.BadRequestException;
import com.appointment.auth.exceptions.CustomGenericException;
import com.appointment.auth.repository.UserRepository;
import com.appointment.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Value("${spring.security.auth0.security-key}")
    private String securityKey;
    @Override
    public UserDTORes validateLoginUser(UserDTOReq userDTOReq) throws CustomGenericException {
        if (!Objects.equals(userDTOReq.getSecurityKey(), securityKey))
            throw new BadRequestException("Error al guardar el usuario", "La clave de seguridad es incorrecta");
        User userFound = userRepository.findByEmail(userDTOReq.getEmail());

        if(userFound != null){
            if(userFound.getStatus().getName().equals(StatusEnum.DISABLED.getValue()))
                throw new CustomGenericException(HttpStatus.CONFLICT.toString(), "Disabled user.", "This user is disabled from my-appointments services.", HttpStatus.CONFLICT);
        }else{
            userRepository.save(User.builder()
                .email(userDTOReq.getEmail())
                .auth0Id(userDTOReq.getUserId())
                .role(Role.builder().id(1L).build())
                .status(Status.builder().id(1L).build())
                .build());
        }

        return UserDTORes.builder()
            .email(userDTOReq.getEmail())
            .build();
    }
}
