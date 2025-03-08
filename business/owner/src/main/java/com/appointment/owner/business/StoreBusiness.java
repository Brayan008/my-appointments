package com.appointment.owner.business;


import com.appointment.commons.dtos.request.StoreRequest;
import com.appointment.commons.dtos.response.StoreResponse;

import java.util.List;

public interface StoreBusiness {
    List<StoreResponse> getStores();

    StoreResponse getStoreById(Long storeId);

    StoreResponse createStore(StoreRequest store);

    StoreResponse updateStore(StoreRequest store, Long storeId);

    StoreResponse disableById(Long storeId);

    StoreResponse enableById(Long storeId);

    List<StoreResponse> getEnabledStore();

    List<StoreResponse> getDisabledStore();
}
