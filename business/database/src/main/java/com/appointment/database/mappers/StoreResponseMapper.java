package com.appointment.database.mappers;

import com.appointment.commons.dtos.response.StoreResponse;
import com.appointment.database.entities.StoreEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreResponseMapper {

    StoreResponse storeEntityToStoreResponse(StoreEntity storeEntity);

    StoreEntity storeResponseToStoreEntity(StoreResponse storeResponse);

    List<StoreResponse> storeEntitiesToStoreResponses(List<StoreEntity> storeEntities);
}
