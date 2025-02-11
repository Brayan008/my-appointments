package com.appointment.owner.repositories;

import com.appointment.owner.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
