package com.appointment.database.services.impl;

import com.appointment.commons.enums.Status;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.ServiceEntity;
import com.appointment.database.repositories.ServiceRepository;
import com.appointment.database.services.ServiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Override
    public List<ServiceEntity> getServices() {
        return serviceRepository.findAll();
    }

    @Override
    public ServiceEntity getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId)
            .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
                "service not found with id " + serviceId, HttpStatus.NOT_FOUND));
    }

    @Override
    public ServiceEntity createService(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    @Override
    public ServiceEntity updateService(ServiceEntity service, Long serviceId) {
        ServiceEntity currentService = this.getServiceById(serviceId);
        currentService.setName(service.getName());
        currentService.setStatusId(service.getStatusId());
        currentService.setPrice(service.getPrice());
        currentService.setStatusId(service.getStatusId());
        return this.createService(currentService);
    }

    @Override
    public ServiceEntity disableById(Long serviceId) {
        ServiceEntity currentService = this.getServiceById(serviceId);
        currentService.setStatusId(Status.DISABLED.getCode());
        return this.createService(currentService);
    }

    @Override
    public ServiceEntity enableById(Long serviceId) {
        ServiceEntity currentService = this.getServiceById(serviceId);
        currentService.setStatusId(Status.ENABLED.getCode());
        return this.createService(currentService);
    }

    @Override
    public List<ServiceEntity> findByStatusId(Long statusId) {
        return serviceRepository.findByStatusId(statusId);
    }

}
