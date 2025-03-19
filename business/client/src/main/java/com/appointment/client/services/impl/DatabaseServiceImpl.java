package com.appointment.client.services.impl;

import com.appointment.client.services.DatabaseService;
import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.StandardizedApiExceptionResponse;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.response.ClientDBAppointmentResponse;
import com.appointment.commons.exceptions.BusinessException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
            .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(StandardizedApiExceptionResponse.class)
                .flatMap(error -> {
                    log.error("Error on create client appointment {}", error);
                    return Mono.error(new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), (HttpStatus) res.statusCode()));
                })
            )
            .bodyToMono(ClientDBAppointmentResponse.class)
            .flatMap(res -> {
                log.info("Create client appointment {}", res);
                return Mono.just(res);
            });
    }

    @Override
    public Mono<GenericResponse> addFavoriteStore(Long storeId, String email) {
        return webClient
            .post()
            .uri("/stores/"+storeId+"/favorite-store/user/"+email)
            .retrieve()
            .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(StandardizedApiExceptionResponse.class)
                .flatMap(error -> {
                    log.error("Error on add favorite store {}", error);
                    return Mono.error(new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), (HttpStatus) res.statusCode()));
                })
            )
            .bodyToMono(GenericResponse.class)
            .flatMap(res -> {
                log.info("Add favorite store {}", res);
                return Mono.just(res);
            });
    }

    @Override
    public Mono<GenericResponse> deleteFavoriteStore(Long userFavoriteStoreId) {
        return webClient
            .delete()
            .uri("/stores/favorite-store/"+userFavoriteStoreId)
            .retrieve()
            .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(StandardizedApiExceptionResponse.class)
                .flatMap(error -> {
                    log.error("Error on delete favorite store {}", error);
                    return Mono.error(new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), (HttpStatus) res.statusCode()));
                })
            )
            .bodyToMono(GenericResponse.class)
            .flatMap(res -> {
                log.info("Delete favorite store {}", res);
                return Mono.just(res);
            });
    }

}
