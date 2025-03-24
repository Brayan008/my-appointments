package com.appointment.client.business.impl;

import com.appointment.client.business.StoreBusiness;
import com.appointment.client.dtos.StoreResponse;
import com.appointment.client.services.Auth0Service;
import com.appointment.client.services.DatabaseService;
import com.appointment.commons.dtos.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StoreBusinessImpl implements StoreBusiness {

    private final Auth0Service auth0Service;
    private final DatabaseService databaseService;

    @Override
    public Mono<GenericResponse> addStoreToFavorites(String accessToken, Long storeId) {
        return this.auth0Service.getUserInfo(accessToken)
            .flatMap(userInfo ->
                databaseService.addFavoriteStore(storeId, userInfo.getEmail())
            );
    }

    @Override
    public Mono<GenericResponse> deleteFavoriteStore(Long userFavoriteStoreId) {
        return this.databaseService.deleteFavoriteStore(userFavoriteStoreId);
    }

   @Override
   public Flux<StoreResponse> findStoresBySearchText(String searchText) {
      return this.databaseService.findStoresBySearchText(searchText);
   }
}
