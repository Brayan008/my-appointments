package com.appointment.database.services;

import com.appointment.database.entities.ClientAppointmentEntity;
import com.appointment.database.entities.UserEntity;
import org.springframework.data.domain.Page;

public interface ClientAppointmentsService {
   ClientAppointmentEntity createClientAppointment(ClientAppointmentEntity clientAppointmentEntity);
   Page<ClientAppointmentEntity> findClientAppointment(UserEntity userEntity, int page, int size);
}
