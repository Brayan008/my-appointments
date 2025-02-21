package com.appointment.database.repositories;

import com.appointment.database.entities.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    List<StoreEntity> findByStatusId(Long statusId);
}
