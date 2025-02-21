package com.appointment.database.services.impl;

import com.appointment.commons.enums.Status;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.StoreEntity;
import com.appointment.database.repositories.StoreRepository;
import com.appointment.database.services.StoreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public List<StoreEntity> getStores() {
        return storeRepository.findAll();
    }

    @Override
    public StoreEntity getStoreById(Long storeId) {
        return storeRepository.findById(storeId)
            .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
                "store not found with id " + storeId, HttpStatus.NOT_FOUND));
    }

    @Override
    public StoreEntity createStore(StoreEntity store) {
        return storeRepository.save(store);
    }

    @Override
    public StoreEntity updateStore(StoreEntity store, Long storeId) {
        StoreEntity currentStore = this.getStoreById(storeId);
        currentStore.setName(store.getName());
        currentStore.setAddress(store.getAddress());
        currentStore.setDescription(store.getDescription());
        currentStore.setCoordinates(store.getCoordinates());
        currentStore.setStatusId(store.getStatusId());
        currentStore.setCompanyId(store.getCompanyId());
        return this.createStore(currentStore);
    }

    @Override
    public StoreEntity disableById(Long storeId) {
        StoreEntity currentStore = this.getStoreById(storeId);
        currentStore.setStatusId(Status.DISABLED.getCode());
        return this.createStore(currentStore);
    }

    @Override
    public StoreEntity enableById(Long storeId) {
        StoreEntity currentStore = this.getStoreById(storeId);
        currentStore.setStatusId(Status.ENABLED.getCode());
        return this.createStore(currentStore);
    }

    @Override
    public List<StoreEntity> findByStatusId(Long statusId) {
        return storeRepository.findByStatusId(statusId);
    }
}
