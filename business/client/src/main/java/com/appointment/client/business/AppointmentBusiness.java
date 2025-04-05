package com.appointment.client.business;

import com.appointment.client.dtos.NewAppointmentReq;
import com.appointment.client.dtos.PageableClientAppointmentResponse;
import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import reactor.core.publisher.Mono;

public interface AppointmentBusiness {

    Mono<String> newAppointment(String token, NewAppointmentReq newAppointmentReq);
    Mono<PageableClientAppointmentResponse> findClientAppointments(String token, int page, int size);
    Mono<GenericResponse> addRateAppointment(String token, Long idClientAppointment, RateAppointmentRequest rateAppointmentRequest);
    Mono<GenericResponse> updateRateAppointment(String token, Long idRateAppointment, RateAppointmentRequest rateAppointmentRequest);

}
