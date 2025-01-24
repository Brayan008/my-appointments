package com.appointment.gatewayserver.service;

import com.appointment.gatewayserver.dto.AuthTokenResponse;
import com.appointment.gatewayserver.dto.UserAuthResDTO;
import reactor.core.publisher.Mono;

public interface Auth0Service {

    Mono<AuthTokenResponse> getApiToken(String code, String redirectUri);
    Mono<UserAuthResDTO> getUserInfo(String accessToken);

}
