package com.appointment.auth.service.impl;

import com.appointment.auth.dto.AuthTokenRequest;
import com.appointment.auth.dto.AuthTokenResponse;
import com.appointment.auth.dto.UserAuthResDTO;
import com.appointment.auth.service.Auth0Service;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class Auth0ServicesImpl implements Auth0Service {
    @Override
    public Mono<AuthTokenResponse> getApiToken(String code, String redirectUri) {
        return null;
    }

    @Override
    public Mono<UserAuthResDTO> getUserInfo(String accessToken) {
        return null;
    }
/*
    private WebClient webClient;

    @Value("${spring.security.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.auth0.client-secret}")
    private String clientSecret;

    @Value("${spring.security.auth0.domain}")
    private String domain;

    @PostConstruct
    public void init(){
        this.webClient = WebClient.builder()
                .baseUrl(domain)
                .build();
    }


    @Override
    public Mono<AuthTokenResponse> getApiToken(String code, String redirectUri) {
        return webClient.post()
                .uri("/oauth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(AuthTokenRequest.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .code(code)
                        .redirectUri(redirectUri)
                        .grantType("authorization_code")
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(String.class)
                        .flatMap(error -> {
                            log.error("Error on get token {}", error);
                            return Mono.error(new Exception(String.valueOf(error)));
                        })
                )
                .bodyToMono(AuthTokenResponse.class);
    }

    @Override
    public Mono<UserAuthResDTO> getUserInfo(String accessToken) {
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
                .bodyToMono(UserAuthResDTO.class);
    }*/
}
