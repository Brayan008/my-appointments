package com.appointment.database.repositories;

import com.appointment.database.entities.ClientDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDatesRepository extends JpaRepository<ClientDateEntity, Long> {
}
