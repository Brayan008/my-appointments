package com.appointment.database.repositories;

import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreEmployeeRepository extends JpaRepository<StoreEmployeeEntity, Long> {
   List<StoreEmployeeEntity> findByStatusId(Long statusId);

   @Query("SELECT e FROM StoreEmployeeEntity e WHERE e.store.storeId = :storeId")
   List<StoreEmployeeEntity> findEmployeesByStoreId(@Param("storeId") Long storeId);

   Optional<StoreEmployeeEntity> findByStoreIdAndUserId(Long storeId, Long userId);
}
