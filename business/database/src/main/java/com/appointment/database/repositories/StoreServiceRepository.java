package com.appointment.database.repositories;

import com.appointment.database.entities.StoreServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreServiceRepository extends JpaRepository<StoreServiceEntity, Long> {
   List<StoreServiceEntity> findByStatusId(Long statusId);
}
