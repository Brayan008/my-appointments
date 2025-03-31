package com.appointment.database.repositories;

import com.appointment.database.entities.ClientAppointmentEntity;
import com.appointment.database.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClientAppointmentsRepository extends JpaRepository<ClientAppointmentEntity, Long> {

   @Query(value = "SELECT COUNT(*) > 0 FROM client_appointments WHERE user_appointment = :userDate AND status_appointment_id != 2", nativeQuery = true)
   boolean existsActiveUserDate(LocalDateTime userDate);

   @Query(value = """
       SELECT COUNT(*) FROM client_appointments ca
       WHERE DATE(ca.user_appointment) = DATE(:userDate)
       AND ca.client_id = :clientId
       AND ca.status_appointment_id != 2
       """, nativeQuery = true)
   int countAppointmentsByClientAndDay(LocalDateTime userDate,Long clientId);

   Page<ClientAppointmentEntity> findByClient(UserEntity userEntity, Pageable pageable);

   @Query(value = """
       SELECT * FROM client_appointments ca
       WHERE ca.client_appointment_id = :id AND ca.status_appointment_id = :statusAppointmentId
       """, nativeQuery = true)
   ClientAppointmentEntity findClientAppointmentByIdAndStatus(Long id, Long statusAppointmentId);

}
