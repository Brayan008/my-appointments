package com.appointment.database.business;

import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.database.entities.ClientAppointmentEntity;
import org.springframework.data.domain.Page;

public interface ClientAppointmentBusiness {
   ClientAppointmentEntity createClientAppointment(ClientDBAppointmentRequest clientDBAppointmentRequest);
   Page<ClientAppointmentEntity> findClientAppointment(String email, int page, int size);

}
