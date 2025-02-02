package com.appointment.owner.repositories;

import com.appointment.owner.entities.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
}
