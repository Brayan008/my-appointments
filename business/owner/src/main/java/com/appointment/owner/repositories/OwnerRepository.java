package com.appointment.owner.repositories;

import com.appointment.owner.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
}
