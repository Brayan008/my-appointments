package com.appointment.client.business.impl;

import com.appointment.client.business.AppointmentBusiness;
import com.appointment.client.dtos.NewAppointmentReq;
import com.appointment.client.dtos.PageableClientAppointmentResponse;
import com.appointment.client.services.DatabaseService;
import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import com.appointment.commons.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AppointmentBusinessImpl implements AppointmentBusiness {

    private final DatabaseService databaseService;
    private final JwtUtils jwtUtils;

    @Override
    public Mono<String> newAppointment(String token, NewAppointmentReq newAppointmentReq) {
        return this.databaseService.createClientAppointment(ClientDBAppointmentRequest.builder()
              .serviceId(newAppointmentReq.getServiceId())
              .userAppointment(newAppointmentReq.getUserAppointment())
              .storeEmployeeId(newAppointmentReq.getStoreEmployeeId())
              .clientEmail(this.jwtUtils.getJwtStructure(token).getEmail())
              .build())
           .map(res -> "SUCCESS");
    }

   @Override
   public Mono<PageableClientAppointmentResponse> findClientAppointments(String token, int page, int size) {
      return databaseService.findClientAppointments(this.jwtUtils.getJwtStructure(token).getEmail(), page, size);
   }

   @Override
   public Mono<GenericResponse> addRateAppointment(String token, Long idClientAppointment, RateAppointmentRequest rateAppointmentRequest) {
      rateAppointmentRequest.setEmailClient(this.jwtUtils.getJwtStructure(token).getEmail());
      return this.databaseService.addRateAppointment(idClientAppointment, rateAppointmentRequest);
   }

   @Override
   public Mono<GenericResponse> updateRateAppointment(String token, Long idRateAppointment, RateAppointmentRequest rateAppointmentRequest) {
      rateAppointmentRequest.setEmailClient(this.jwtUtils.getJwtStructure(token).getEmail());
      return this.databaseService.updateRateAppointment(idRateAppointment, rateAppointmentRequest);
   }
}
