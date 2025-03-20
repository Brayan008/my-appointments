package com.appointment.database.services;

import com.appointment.database.entities.ServiceEntity;

import java.util.List;

public interface ServiceService {
   List<ServiceEntity> getServices();

   ServiceEntity getServiceById(Long serviceId);

   ServiceEntity createService(ServiceEntity service);

   ServiceEntity updateService(ServiceEntity service, Long serviceId);

   ServiceEntity disableById(Long serviceId);

   ServiceEntity enableById(Long serviceId);

   List<ServiceEntity> findByStatusId(Long statusId);
}
