package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.response.StoreResponse;
import com.appointment.owner.entities.StoreEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreResponseMapper {

    StoreResponse storeEntityToStoreResponse(StoreEntity storeEntity);

    StoreEntity storeResponseToStoreEntity(StoreResponse storeResponse);

    List<StoreResponse> storeEntitiesToStoreResponses(List<StoreEntity> storeEntities);
}
