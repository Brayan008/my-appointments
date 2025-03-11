package com.appointment.database.repositories;

import com.appointment.database.entities.ClientAppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ClientAppointmentsRepository extends JpaRepository<ClientAppointmentEntity, Long> {

    @Query(value = "SELECT COUNT(*) > 0 FROM client_appointments WHERE user_date = :userDate AND status_date_id != 2", nativeQuery = true)
    boolean existsActiveUserDate(LocalDateTime userDate);

    @Query(value = """
        SELECT COUNT(*) FROM client_dates cd
        WHERE DATE(cd.user_date) = DATE(:userDate)
        AND cd.client_id = :clientId
        AND cd.status_date_id != 2
        """, nativeQuery = true)
    int countAppointmentsByClientAndDay(LocalDateTime userDate,Long clientId);

}
