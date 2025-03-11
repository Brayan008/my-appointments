package com.appointment.client.services.impl;

import com.appointment.client.services.Auth0Service;
import com.appointment.commons.dtos.response.UserAuthResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class Auth0ServiceImpl implements Auth0Service {
    private WebClient webClient;

    @Value("${auth0.domain}")
    private String domain;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
            .baseUrl(domain)
            .build();
    }

    @Override
    public Mono<UserAuthResponse> getUserInfo(String accessToken) {
        return webClient.get()
            .uri("/userinfo")
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(String.class)
                .flatMap(error -> {
                    log.error("Error on get token {}", error);
                    return Mono.error(new Exception(String.valueOf(error)));
                })
            )
            .bodyToMono(UserAuthResponse.class)
            .doOnNext(res-> log.info(String.valueOf(res)));
    }
}
