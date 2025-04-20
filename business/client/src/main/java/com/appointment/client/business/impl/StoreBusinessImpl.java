package com.appointment.client.business.impl;

import com.appointment.client.business.StoreBusiness;
import com.appointment.client.dtos.StoreEmployeeResponse;
import com.appointment.client.dtos.StoreResponse;
import com.appointment.client.services.DatabaseService;
import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StoreBusinessImpl implements StoreBusiness {

    private final DatabaseService databaseService;
    private final JwtUtils jwtUtils;

    @Override
    public Mono<GenericResponse> addStoreToFavorites(String token, Long storeId) {
        return this.databaseService.addFavoriteStore(storeId, jwtUtils.getJwtStructure(token).getEmail());
    }

    @Override
    public Mono<GenericResponse> deleteFavoriteStore(Long userFavoriteStoreId) {
        return this.databaseService.deleteFavoriteStore(userFavoriteStoreId);
    }

   @Override
   public Flux<StoreResponse> findStoresBySearchText(String searchText, Double lat, Double lng, Integer radius) {
      return this.databaseService.findStoresBySearchText(searchText, lat, lng, radius);
   }

   @Override
   public Flux<StoreEmployeeResponse> getEmployeesOfStore(Long storeId) {
      return this.databaseService.getEmployeesOfStore(storeId);
   }
}
