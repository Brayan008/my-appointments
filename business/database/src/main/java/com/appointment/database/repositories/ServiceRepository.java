package com.appointment.database.repositories;

import com.appointment.database.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
   List<ServiceEntity> findByStatusId(Long statusId);
}
