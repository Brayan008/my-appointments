package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.request.StoreRequest;
import com.appointment.owner.entities.StoreEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreRequestMapper {

    StoreEntity storeRequestToStoreEntity(StoreRequest storeRequest);

    StoreRequest storeEntityToStoreRequest(StoreEntity storeEntity);

    List<StoreEntity> storeRequestsToStoreEntities(List<StoreRequest> storeRequests);
}
