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
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Value("${spring.security.auth0.security-key}")
    private String securityKey;
    @Override
    public Mono<UserDTORes> validateLoginUser(UserDTOReq userDTOReq) {
        if (!Objects.equals(userDTOReq.getSecurityKey(), securityKey)) {
            return Mono.error(new BadRequestException(
                "Error al validar el usuario",
                "La clave de seguridad es incorrecta"
            ));
        }

        return userRepository.findByEmail(userDTOReq.getEmail())
            .flatMap(userFound -> {
                if (userFound.getStatusId().equals(StatusEnum.DISABLED.getId())) {
                    return Mono.error(new CustomGenericException(
                        "This user is disabled",
                        "This user is disabled from my-appointments services.",
                        HttpStatus.CONFLICT.toString(),
                        HttpStatus.CONFLICT
                    ));
                }
                return Mono.just(userFound);
            })
            .switchIfEmpty(
                userRepository.save(
                    User.builder()
                        .email(userDTOReq.getEmail())
                        .roleId(1L)
                        .statusId(1L)
                        .build()
                )
            )
            .map(user -> UserDTORes.builder()
                .email(user.getEmail())
                .build()
            );
    }
}
