package com.appointment.auth.service;

import com.appointment.auth.dto.UserAuthResDTO;
import reactor.core.publisher.Mono;

public interface Auth0Service {
    Mono<UserAuthResDTO> getUserInfo(String accessToken);

}
