package com.appointment.database.repositories;

import com.appointment.database.entities.ClientAppointmentEntity;
import com.appointment.database.entities.RateAppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateAppointmentRepository extends JpaRepository<RateAppointmentEntity, Long> {

   RateAppointmentEntity findByClientAppointment(ClientAppointmentEntity clientAppointment);

}
