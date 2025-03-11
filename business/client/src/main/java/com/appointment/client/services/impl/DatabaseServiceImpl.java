package com.appointment.client.services.impl;

import com.appointment.client.services.DatabaseService;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.response.ClientDBAppointmentResponse;
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
public class DatabaseServiceImpl implements DatabaseService {

    private WebClient webClient;

    @Value("${microservices.endpoint.database}")
    private String domain;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
            .baseUrl(domain)
            .build();
    }

    @Override
    public Mono<ClientDBAppointmentResponse> createClientAppointment(ClientDBAppointmentRequest clientDBAppointmentRequest) {
        return webClient
            .post()
            .uri("/client-appointment")
            .retrieve()
            .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(String.class)
                .flatMap(error -> {
                    log.error("Error on database token {}", error);
                    return Mono.error(new Exception(String.valueOf(error)));
                })
            )
            .bodyToMono(ClientDBAppointmentResponse.class)
            .flatMap(res -> {
                log.info("Create client appointment {}", res);
                return Mono.just(res);
            });
    }

}
