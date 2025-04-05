package com.appointment.database.services;

import com.appointment.database.entities.StoreServiceEntity;

import java.util.List;

public interface StoreServiceService {
   List<StoreServiceEntity> getServices();

   StoreServiceEntity getServiceById(Long serviceId);

   StoreServiceEntity createService(StoreServiceEntity service);

   StoreServiceEntity updateService(StoreServiceEntity service, Long serviceId);

   StoreServiceEntity disableById(Long serviceId);

   StoreServiceEntity enableById(Long serviceId);

   List<StoreServiceEntity> findByStatusId(Long statusId);
}
