package com.appointment.client.business;

import com.appointment.client.dtos.NewAppointmentReq;
import com.appointment.client.dtos.PageableClientAppointmentResponse;
import reactor.core.publisher.Mono;

public interface AppointmentBusiness {

    Mono<String> newAppointment(String accessToken, NewAppointmentReq newAppointmentReq);
    Mono<PageableClientAppointmentResponse> findClientAppointments(String accessToken, int page, int size);

}
