package com.appointment.client.services;

import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.response.ClientDBAppointmentResponse;
import reactor.core.publisher.Mono;

public interface DatabaseService {

    Mono<ClientDBAppointmentResponse> createClientAppointment(ClientDBAppointmentRequest clientDBAppointmentRequest);
    Mono<GenericResponse> addFavoriteStore(Long storeId, String email);
    Mono<GenericResponse> deleteFavoriteStore(Long userFavoriteStoreId);
}

