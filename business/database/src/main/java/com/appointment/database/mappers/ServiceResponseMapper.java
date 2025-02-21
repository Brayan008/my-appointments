package com.appointment.database.mappers;

import com.appointment.commons.dtos.response.ServiceResponse;
import com.appointment.database.entities.ServiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceResponseMapper {

    ServiceResponse serviceEntityToServiceResponse(ServiceEntity serviceEntity);

    ServiceEntity serviceResponseToServiceEntity(ServiceResponse serviceResponse);

    List<ServiceResponse> serviceEntitiesToServiceResponses(List<ServiceEntity> serviceEntities);
}
