package com.appointment.client.services;

import com.appointment.client.dtos.PageableClientAppointmentResponse;
import com.appointment.client.dtos.StoreResponse;
import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.response.ClientDBAppointmentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DatabaseService {

    Mono<ClientDBAppointmentResponse> createClientAppointment(ClientDBAppointmentRequest clientDBAppointmentRequest);
    Mono<GenericResponse> addFavoriteStore(Long storeId, String email);
    Mono<GenericResponse> deleteFavoriteStore(Long userFavoriteStoreId);
    Flux<StoreResponse> findStoresBySearchText(String searchText);
    Mono<PageableClientAppointmentResponse> findClientAppointments(String email, int page, int size);
}

