package com.appointment.client.services;

import com.appointment.commons.dtos.response.UserAuthResponse;
import reactor.core.publisher.Mono;

public interface Auth0Service {
    Mono<UserAuthResponse> getUserInfo(String accessToken);

}
