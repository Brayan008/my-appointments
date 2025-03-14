package com.appointment.database.controllers;

import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.database.business.ClientAppointmentBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Client Appointment", description = "This APi serve all functionality for management clients appointment")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/client-appointment")
public class ClientAppointmentController {

    private final ClientAppointmentBusiness clientAppointmentBusiness;

    @Operation(summary = "Create an appointment to client")
    @PostMapping()
    public ResponseEntity<?> createClientAppointment(@RequestBody ClientDBAppointmentRequest clientDBAppointmentRequest){
        log.info("create: client appoitnment {}", clientDBAppointmentRequest);
        return new ResponseEntity<>(this.clientAppointmentBusiness.createClientAppointment(clientDBAppointmentRequest), HttpStatus.CREATED);
    }


}
