package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.request.OwnerRequest;
import com.appointment.owner.entities.OwnerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerRequestMapper {

    OwnerEntity ownerRequestToOwnerEntity(OwnerRequest ownerRequest);

    OwnerRequest ownerEntityToOwnerRequest(OwnerEntity ownerEntity);

    List<OwnerEntity> ownerRequestsToOwnerEntities(List<OwnerRequest> ownerRequests);
}
