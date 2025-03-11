package com.appointment.owner.business;


import com.appointment.commons.dtos.request.ServiceRequest;
import com.appointment.commons.dtos.response.ServiceResponse;

import java.util.List;

public interface ServiceBusiness {
    List<ServiceResponse> getServices();

    ServiceResponse getServiceById(Long serviceId);

    ServiceResponse createService(ServiceRequest service);

    ServiceResponse updateService(ServiceRequest service, Long serviceId);

    ServiceResponse disableById(Long serviceId);

    ServiceResponse enableById(Long serviceId);

    List<ServiceResponse> getEnabledService();

    List<ServiceResponse> getDisabledService();
}
