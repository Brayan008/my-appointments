package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.request.ServiceRequest;
import com.appointment.owner.entities.ServiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceRequestMapper {

    ServiceEntity serviceRequestToServiceEntity(ServiceRequest serviceRequest);

    ServiceRequest serviceEntityToServiceRequest(ServiceEntity serviceEntity);

    List<ServiceEntity> serviceRequestsToServiceEntities(List<ServiceRequest> serviceRequests);
}
