package com.appointment.database.business;

import com.appointment.commons.dtos.GenericResponse;
import com.appointment.database.entities.UserFavoriteStoresEntity;

public interface UserFavoriteStoreBusiness {

    GenericResponse addFavoriteStore(Long storeId, String emailUser);

    GenericResponse deleteFavoriteStore(Long favoriteStoreId);


}
