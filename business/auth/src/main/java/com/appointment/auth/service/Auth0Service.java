package com.appointment.auth.service;

import com.appointment.auth.dto.AuthTokenResponse;
import com.appointment.auth.dto.UserAuthResDTO;
import reactor.core.publisher.Mono;

public interface Auth0Service {

    Mono<AuthTokenResponse> getApiToken(String code, String redirectUri);
    Mono<UserAuthResDTO> getUserInfo(String accessToken);

}
