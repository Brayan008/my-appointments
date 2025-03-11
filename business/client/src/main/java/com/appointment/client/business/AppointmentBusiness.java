package com.appointment.client.business;

import com.appointment.client.dtos.NewAppointmentReq;
import reactor.core.publisher.Mono;

public interface AppointmentBusiness {

    Mono<String> newAppointment(String accessToken, NewAppointmentReq newAppointmentReq);

}
