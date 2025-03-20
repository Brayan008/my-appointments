package com.appointment.database.repositories;

import com.appointment.database.entities.StoreEntity;
import com.appointment.database.entities.UserEntity;
import com.appointment.database.entities.UserFavoriteStoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteStoreRepository extends JpaRepository<UserFavoriteStoresEntity, Long> {

   UserFavoriteStoresEntity findByUserAndStore(UserEntity user, StoreEntity store);

   StoreEntity user(UserEntity user);
}
