package com.appointment.client.controllers;

import com.appointment.client.business.AppointmentBusiness;
import com.appointment.client.dtos.NewAppointmentReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        description = "This endpoint send new client appointment."
    )
    @PostMapping()
    public Mono<String> newAppointment(
        @RequestBody NewAppointmentReq newAppointmentReq,
        @RequestHeader("AccessToken") String accessToken) {
        return appointmentBusiness.newAppointment(accessToken, newAppointmentReq);
    }


}
