package com.appointment.database.controllers;

import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.database.business.ClientAppointmentBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

   @Operation(summary = "Find client appointments by email")
   @GetMapping("/{email}")
   public ResponseEntity<?> findClientAppointment(@PathVariable String email,
                                                  @RequestParam(required = false, defaultValue = "0") int page,
                                                  @RequestParam(required = false, defaultValue = "10") int size){
      log.info("Find client appointments by email: {}", email);
      return new ResponseEntity<>(this.clientAppointmentBusiness.findClientAppointment(email, page, size), HttpStatus.OK);
   }
}
