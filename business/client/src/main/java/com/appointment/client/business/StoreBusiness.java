package com.appointment.client.business;

import com.appointment.commons.dtos.GenericResponse;
import reactor.core.publisher.Mono;

public interface StoreBusiness {

    Mono<GenericResponse> addStoreToFavorites(String accessToken, Long idStore);
    Mono<GenericResponse> deleteFavoriteStore(Long userFavoriteStoreId);
}
