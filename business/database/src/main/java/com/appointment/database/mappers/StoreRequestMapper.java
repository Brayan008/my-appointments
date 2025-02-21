package com.appointment.database.mappers;

import com.appointment.commons.dtos.request.StoreRequest;
import com.appointment.database.entities.StoreEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreRequestMapper {

    StoreEntity storeRequestToStoreEntity(StoreRequest storeRequest);

    StoreRequest storeEntityToStoreRequest(StoreEntity storeEntity);

    List<StoreEntity> storeRequestsToStoreEntities(List<StoreRequest> storeRequests);
}
