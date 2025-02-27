package com.appointment.database.repositories;

import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreEmployeeRepository extends JpaRepository<StoreEmployeeEntity, Long> {
    List<StoreEmployeeEntity> findByStatusId(Long statusId);

    @Query("SELECT e.user FROM StoreEmployeeEntity e WHERE e.store.id = :storeId")
    List<UserEntity> findEmployeesByStoreId(@Param("storeId") Long storeId);
}
