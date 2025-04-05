package com.appointment.client.controllers;

import com.appointment.client.business.AppointmentBusiness;
import com.appointment.client.dtos.NewAppointmentReq;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/client/appointment")
@Tag(name = "Appointments Controller", description = "Endpoints management appointments.")
public class AppointmentController {

    private final AppointmentBusiness appointmentBusiness;

    @Operation(
       summary = "Register new client appointment",
       description = "This endpoint send new client appointment.",
       security = @SecurityRequirement(name = "bearer-jwt")
    )
    @PostMapping()
    public Mono<String> newAppointment(
        @RequestBody NewAppointmentReq newAppointmentReq,
        @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return appointmentBusiness.newAppointment(token, newAppointmentReq);
    }

   @Operation(
      summary = "Find all client appointments.",
      description = "This endpoint find all client appointments.",
      security = @SecurityRequirement(name = "bearer-jwt")
   )
   @GetMapping()
   public ResponseEntity<?> findClientAppointments(
      @Parameter(hidden = true) @RequestHeader("Authorization") String token,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size){
      log.info("Find client appointments");
      return new ResponseEntity<>(this.appointmentBusiness.findClientAppointments(token, page, size), HttpStatus.OK);
   }

   @Operation(
      summary = "Rate client appointment.",
      description = "This endpoint rate the client appointment.",
      security = @SecurityRequirement(name = "bearer-jwt")
   )
   @PostMapping("/{idClientAppointment}/rate")
   public ResponseEntity<?> rateClientAppointment(@Parameter(hidden = true) @RequestHeader("Authorization") String token,
                                                  @PathVariable Long idClientAppointment,
                                                  @RequestBody RateAppointmentRequest rateAppointmentRequest){
      log.info("Rate client appointment: {} {}", idClientAppointment, rateAppointmentRequest);
      return new ResponseEntity<>(this.appointmentBusiness.addRateAppointment(token, idClientAppointment, rateAppointmentRequest), HttpStatus.OK);
   }

   @Operation(
      summary = "Update rate client appointment.",
      description = "This endpoint update the rate client appointment.",
      security = @SecurityRequirement(name = "bearer-jwt")
   )
   @PutMapping("/rate/{idRateAppointment}")
   public ResponseEntity<?> updateRateClientAppointment(@Parameter(hidden = true) @RequestHeader("Authorization") String token,
                                                        @PathVariable Long idRateAppointment,
                                                        @RequestBody RateAppointmentRequest rateAppointmentRequest){
      log.info("Update rate client appointment: {} {}", idRateAppointment, rateAppointmentRequest);
      return new ResponseEntity<>(this.appointmentBusiness.updateRateAppointment(token, idRateAppointment, rateAppointmentRequest), HttpStatus.OK);
   }


}
