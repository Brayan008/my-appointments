package com.appointment.database.mappers;

import com.appointment.commons.dtos.request.OwnerRequest;
import com.appointment.database.entities.OwnerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerRequestMapper {

    OwnerEntity ownerRequestToOwnerEntity(OwnerRequest ownerRequest);

    OwnerRequest ownerEntityToOwnerRequest(OwnerEntity ownerEntity);

    List<OwnerEntity> ownerRequestsToOwnerEntities(List<OwnerRequest> ownerRequests);
}
