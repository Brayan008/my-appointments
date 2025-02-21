package com.appointment.database.mappers;

import com.appointment.commons.dtos.response.OwnerResponse;
import com.appointment.database.entities.OwnerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerResponseMapper {

    OwnerResponse ownerEntityToOwnerResponse(OwnerEntity ownerEntity);

    OwnerEntity ownerResponseToOwnerEntity(OwnerResponse ownerResponse);

    List<OwnerResponse> ownerEntitiesToOwnerResponses(List<OwnerEntity> ownerEntities);
}
