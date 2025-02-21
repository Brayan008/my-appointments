package com.appointment.database.mappers;

import com.appointment.commons.dtos.request.ServiceRequest;
import com.appointment.database.entities.ServiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceRequestMapper {

    ServiceEntity serviceRequestToServiceEntity(ServiceRequest serviceRequest);

    ServiceRequest serviceEntityToServiceRequest(ServiceEntity serviceEntity);

    List<ServiceEntity> serviceRequestsToServiceEntities(List<ServiceRequest> serviceRequests);
}
