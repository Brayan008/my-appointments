package com.appointment.database.repositories;

import com.appointment.database.entities.EmployeeEntity;
import com.appointment.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByStatusId(Long statusId);

    @Query("SELECT e.user FROM EmployeeEntity e WHERE e.store.id = :storeId")
    List<UserEntity> findEmployeesByStoreId(@Param("storeId") Long storeId);
}
