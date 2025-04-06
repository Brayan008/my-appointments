package com.appointment.database.services;

import com.appointment.database.entities.StoreServiceEntity;

import java.util.List;

public interface StoreServiceService {
   List<StoreServiceEntity> getStoreServices();

   StoreServiceEntity getStoreServiceById(Long storeServiceId);

   StoreServiceEntity createStoreService(StoreServiceEntity service);

   StoreServiceEntity updateStoreService(StoreServiceEntity service, Long storeServiceId);

   StoreServiceEntity disableById(Long storeServiceId);

   StoreServiceEntity enableById(Long storeServiceId);

   List<StoreServiceEntity> findByStatusId(Long statusId);
}
