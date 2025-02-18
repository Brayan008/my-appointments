package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.response.OwnerResponse;
import com.appointment.owner.entities.OwnerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerResponseMapper {

    OwnerResponse ownerEntityToOwnerResponse(OwnerEntity ownerEntity);

    OwnerEntity ownerResponseToOwnerEntity(OwnerResponse ownerResponse);

    List<OwnerResponse> ownerEntitiesToOwnerResponses(List<OwnerEntity> ownerEntities);
}
