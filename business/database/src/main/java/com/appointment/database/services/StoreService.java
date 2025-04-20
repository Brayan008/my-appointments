package com.appointment.database.services;

import com.appointment.database.entities.StoreEntity;

import java.util.List;

public interface StoreService {
   List<StoreEntity> getStores();

   StoreEntity getStoreById(Long storeId);

   StoreEntity createStore(StoreEntity store);

   StoreEntity updateStore(StoreEntity store, Long storeId);

   StoreEntity disableById(Long storeId);

   StoreEntity enableById(Long storeId);

   List<StoreEntity> findByStatusId(Long statusId);
   List<StoreEntity> findStoresBySearchText(String searchText, Double lat, Double lng, Integer radius);
}
