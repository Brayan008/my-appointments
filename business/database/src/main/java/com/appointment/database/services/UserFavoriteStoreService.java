package com.appointment.database.services;

import com.appointment.database.entities.UserFavoriteStoresEntity;

public interface UserFavoriteStoreService {
    UserFavoriteStoresEntity createFavoriteStore(UserFavoriteStoresEntity userFavoriteStoresEntity);
    UserFavoriteStoresEntity deleteFavoriteStore(Long userFavoritStoreId);
}
