package com.appointment.client.services.impl;

import com.appointment.client.dtos.PageableClientAppointmentResponse;
import com.appointment.client.dtos.StoreEmployeeResponse;
import com.appointment.client.dtos.StoreResponse;
import com.appointment.client.services.DatabaseService;
import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.StandardizedApiExceptionResponse;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import com.appointment.commons.dtos.response.ClientDBAppointmentResponse;
import com.appointment.commons.exceptions.BusinessException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
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

   @Override
   public Flux<StoreResponse> findStoresBySearchText(String searchText) {
      return webClient
         .get()
         .uri(uriBuilder -> uriBuilder
            .path("/stores/search")
            .queryParam("searchText", searchText)
            .build())
         .retrieve()
         .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(StandardizedApiExceptionResponse.class)
            .flatMap(error -> {
               log.error("Error on get stores by search text {}", error);
               return Mono.error(new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), (HttpStatus) res.statusCode()));
            })
         )
         .bodyToFlux(StoreResponse.class);
   }

   @Override
   public Mono<PageableClientAppointmentResponse> findClientAppointments(String email, int page, int size) {
      return webClient
         .get()
         .uri(uriBuilder -> uriBuilder
            .path("/client-appointment/"+email)
            .queryParam("page", page)
            .queryParam("size", size)
            .build())
         .retrieve()
         .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(StandardizedApiExceptionResponse.class)
            .flatMap(error -> {
               log.error("Error on find client appointments {}", error);
               return Mono.error(new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), (HttpStatus) res.statusCode()));
            })
         )
         .bodyToMono(PageableClientAppointmentResponse.class);
   }

   @Override
   public Mono<GenericResponse> addRateAppointment(Long idClientAppointment, RateAppointmentRequest rateAppointmentRequest) {
      return webClient
         .post()
         .uri("/client-appointment/"+idClientAppointment+"/rate")
         .contentType(MediaType.APPLICATION_JSON)
         .body(Mono.just(rateAppointmentRequest), RateAppointmentRequest.class)
         .retrieve()
         .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(StandardizedApiExceptionResponse.class)
            .flatMap(error -> {
               log.error("Error on add rate appointment {}", error);
               return Mono.error(new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), (HttpStatus) res.statusCode()));
            })
         )
         .bodyToMono(GenericResponse.class)
         .flatMap(res -> {
            log.info("Add rate appointment {}", res);
            return Mono.just(res);
         });
   }

   @Override
   public Mono<GenericResponse> updateRateAppointment(Long idRateAppointment, RateAppointmentRequest rateAppointmentRequest) {
      return webClient
         .put()
         .uri("/client-appointment/rate/"+idRateAppointment)
         .contentType(MediaType.APPLICATION_JSON)
         .body(Mono.just(rateAppointmentRequest), RateAppointmentRequest.class)
         .retrieve()
         .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(StandardizedApiExceptionResponse.class)
            .flatMap(error -> {
               log.error("Error on update rate appointment {}", error);
               return Mono.error(new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), (HttpStatus) res.statusCode()));
            })
         )
         .bodyToMono(GenericResponse.class)
         .flatMap(res -> {
            log.info("Update rate appointment {}", res);
            return Mono.just(res);
         });
   }

   @Override
   public Flux<StoreEmployeeResponse> getEmployeesOfStore(Long storeId) {
      return webClient
         .get()
         .uri("/stores/"+storeId+"/employees")
         .retrieve()
         .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(StandardizedApiExceptionResponse.class)
            .flatMap(error -> {
               log.error("Error on get employees from store {}", error);
               return Mono.error(new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), (HttpStatus) res.statusCode()));
            })
         )
         .bodyToFlux(StoreEmployeeResponse.class);
   }

}
