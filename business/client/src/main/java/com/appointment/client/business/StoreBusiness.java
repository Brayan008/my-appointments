package com.appointment.client.business;

import com.appointment.client.dtos.StoreResponse;
import com.appointment.commons.dtos.GenericResponse;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreBusiness {

    Mono<GenericResponse> addStoreToFavorites(String accessToken, Long idStore);
    Mono<GenericResponse> deleteFavoriteStore(Long userFavoriteStoreId);
    Flux<StoreResponse> findStoresBySearchText(String searchText);
}
