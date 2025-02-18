package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.response.ServiceResponse;
import com.appointment.owner.entities.ServiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceResponseMapper {

    ServiceResponse serviceEntityToServiceResponse(ServiceEntity serviceEntity);

    ServiceEntity serviceResponseToServiceEntity(ServiceResponse serviceResponse);

    List<ServiceResponse> serviceEntitiesToServiceResponses(List<ServiceEntity> serviceEntities);
}
