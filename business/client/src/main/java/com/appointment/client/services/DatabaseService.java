package com.appointment.client.services;

import com.appointment.client.dtos.*;
import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import com.appointment.commons.dtos.response.ClientDBAppointmentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface DatabaseService {

    Mono<ClientDBAppointmentResponse> createClientAppointment(ClientDBAppointmentRequest clientDBAppointmentRequest);
    Mono<GenericResponse> addFavoriteStore(Long storeId, String email);
    Mono<GenericResponse> deleteFavoriteStore(Long userFavoriteStoreId);
    Flux<StoreResponse> findStoresBySearchText(String searchText, Double lat, Double lng, Integer radius);
    Mono<PageableClientAppointmentResponse> findClientAppointments(String email, int page, int size);
    Mono<GenericResponse> addRateAppointment(Long idClientAppointment, RateAppointmentRequest rateAppointmentRequest);
    Mono<GenericResponse> updateRateAppointment(Long idRateAppointment, RateAppointmentRequest rateAppointmentRequest);
    Flux<StoreEmployeeResponse> getEmployeesOfStore(Long storeId);
    Mono<StoreEmployeeResponse> getStoreEmployeeById(Long storeEmployeeId);
    Mono<ConfigEmployeeResponse> findConfigEmployeeByStoreEmployeeIdAndDayOfWeek(Long storeEmployeeId, Integer dayOfWeek);
    Flux<ClientAppointmentResponse> getAppointmentsByStoreEmployeeIdAndDateWithoutStatus(Long storeEmployeeId, LocalDate date, Long excludedStatusId);
}

