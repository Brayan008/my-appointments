package com.appointment.gatewayserver.service.impl;

import com.appointment.gatewayserver.dto.AuthTokenRequest;
import com.appointment.gatewayserver.dto.AuthTokenResponse;
import com.appointment.gatewayserver.dto.UserAuthResDTO;
import com.appointment.gatewayserver.service.Auth0Service;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class Auth0ServicesImpl implements Auth0Service {

    private WebClient webClient;

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @PostConstruct
    public void init(){
        this.webClient = WebClient.builder()
                .baseUrl("https://dev-9pn-820c.us.auth0.com")
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
    }
}
