package com.appointment.client.business.impl;

import com.appointment.client.business.AppointmentBusiness;
import com.appointment.client.dtos.NewAppointmentReq;
import com.appointment.client.dtos.PageableClientAppointmentResponse;
import com.appointment.client.services.Auth0Service;
import com.appointment.client.services.DatabaseService;
import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AppointmentBusinessImpl implements AppointmentBusiness {

    private final Auth0Service auth0Service;
    private final DatabaseService databaseService;

    @Override
    public Mono<String> newAppointment(String accessToken, NewAppointmentReq newAppointmentReq) {
        return this.auth0Service.getUserInfo(accessToken)
            .flatMap(userInfo ->
                databaseService.createClientAppointment(ClientDBAppointmentRequest.builder()
                    .serviceId(newAppointmentReq.getServiceId())
                    .userAppointment(newAppointmentReq.getUserAppointment())
                    .storeEmployeeId(newAppointmentReq.getStoreEmployeeId())
                    .clientEmail(userInfo.getEmail())
                    .build())
                    .map(res -> "SUCCESS")
            );
    }

   @Override
   public Mono<PageableClientAppointmentResponse> findClientAppointments(String accessToken, int page, int size) {
      return this.auth0Service.getUserInfo(accessToken)
         .flatMap(userInfo ->
            databaseService.findClientAppointments(userInfo.getEmail(), page, size));
   }

   @Override
   public Mono<GenericResponse> addRateAppointment(String accessToken, Long idClientAppointment, RateAppointmentRequest rateAppointmentRequest) {
      return this.auth0Service.getUserInfo(accessToken)
         .flatMap(resInfo -> {
            rateAppointmentRequest.setEmailClient(resInfo.getEmail());
            return this.databaseService.addRateAppointment(idClientAppointment, rateAppointmentRequest);
         });
   }

   @Override
   public Mono<GenericResponse> updateRateAppointment(String accessToken, Long idRateAppointment, RateAppointmentRequest rateAppointmentRequest) {
      return this.auth0Service.getUserInfo(accessToken)
         .flatMap(resInfo -> {
            rateAppointmentRequest.setEmailClient(resInfo.getEmail());
            return this.databaseService.updateRateAppointment(idRateAppointment, rateAppointmentRequest);
         });
   }
}
