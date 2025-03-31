package com.appointment.client.business;

import com.appointment.client.dtos.NewAppointmentReq;
import com.appointment.client.dtos.PageableClientAppointmentResponse;
import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import reactor.core.publisher.Mono;

public interface AppointmentBusiness {

    Mono<String> newAppointment(String accessToken, NewAppointmentReq newAppointmentReq);
    Mono<PageableClientAppointmentResponse> findClientAppointments(String accessToken, int page, int size);
    Mono<GenericResponse> addRateAppointment(String accessToken, Long idClientAppointment, RateAppointmentRequest rateAppointmentRequest);
    Mono<GenericResponse> updateRateAppointment(String accessToken, Long idRateAppointment, RateAppointmentRequest rateAppointmentRequest);

}
