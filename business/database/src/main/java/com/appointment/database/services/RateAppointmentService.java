package com.appointment.database.services;

import com.appointment.database.entities.RateAppointmentEntity;

public interface RateAppointmentService {

   RateAppointmentEntity addRateAppointment(RateAppointmentEntity rateAppointment);
   RateAppointmentEntity updateRateAppointment(String emailClient, RateAppointmentEntity rateAppointment);

}
