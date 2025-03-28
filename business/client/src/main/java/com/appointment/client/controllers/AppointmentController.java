package com.appointment.client.controllers;

import com.appointment.client.business.AppointmentBusiness;
import com.appointment.client.dtos.NewAppointmentReq;
import io.swagger.v3.oas.annotations.Operation;
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
        @RequestHeader("AccessToken") String accessToken) {
        return appointmentBusiness.newAppointment(accessToken, newAppointmentReq);
    }

   @Operation(
      summary = "Find all client appointments.",
      description = "This endpoint find all client appointments.",
      security = @SecurityRequirement(name = "bearer-jwt")
   )
   @GetMapping()
   public ResponseEntity<?> findClientAppointments(
      @RequestHeader("AccessToken") String accessToken,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size){
      log.info("Find client appointments");
      return new ResponseEntity<>(this.appointmentBusiness.findClientAppointments(accessToken, page, size), HttpStatus.OK);
   }


}
