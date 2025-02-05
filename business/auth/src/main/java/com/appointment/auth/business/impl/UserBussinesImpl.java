package com.appointment.auth.business.impl;

import com.appointment.auth.business.UserBusiness;
import com.appointment.auth.dto.UserAuthResDTO;
import com.appointment.auth.dto.UserInfoDTORes;
import com.appointment.auth.entities.User;
import com.appointment.auth.repository.UserRepository;
import com.appointment.auth.service.Auth0Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserBussinesImpl implements UserBusiness {

    private final Auth0Service auth0Service;
    private final UserRepository userRepository;

    @Override
    public Mono<UserInfoDTORes> getUserInfo(String accessToken, String email) {
        return Mono.defer(() -> {
            if (email != null) {
                return userRepository.findByEmail(email)
                    .map(dbUser -> buildUserInfo(null, dbUser));
            } else {
                return auth0Service.getUserInfo(accessToken)
                    .flatMap(auth0User ->
                        userRepository.findByEmail(auth0User.getEmail())
                            .map(dbUser -> buildUserInfo(auth0User, dbUser))
                    );
            }
        });
    }

    private UserInfoDTORes buildUserInfo(UserAuthResDTO auth0User, User dbUser) {
        return UserInfoDTORes.builder()
            .idUser(dbUser.getUserId())
            .userAuth0(auth0User)
            .roleId(dbUser.getRoleId())
            .statusId(dbUser.getStatusId())
            .build();
    }
}
