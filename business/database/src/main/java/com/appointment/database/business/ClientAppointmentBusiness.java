package com.appointment.database.business;

import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.database.entities.ClientAppointmentEntity;

public interface ClientAppointmentBusiness {

    ClientAppointmentEntity createClientAppointment(ClientDBAppointmentRequest clientDBAppointmentRequest);


}
