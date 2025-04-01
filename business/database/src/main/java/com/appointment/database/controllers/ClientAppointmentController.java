package com.appointment.database.controllers;

import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import com.appointment.database.business.ClientAppointmentBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

   @Operation(summary = "Rate client appointment")
   @PostMapping("/{idClientAppointment}/rate")
   public ResponseEntity<?> rateClientAppointment(@PathVariable Long idClientAppointment,
                                                  @RequestBody RateAppointmentRequest rateAppointmentRequest){
      log.info("Rate client appointment: {} {}", idClientAppointment, rateAppointmentRequest);
      return new ResponseEntity<>(this.clientAppointmentBusiness.addRateAppointment(idClientAppointment, rateAppointmentRequest), HttpStatus.OK);
   }

   @Operation(summary = "Update rate client appointment")
   @PutMapping("/rate/{idRateAppointment}")
   public ResponseEntity<?> updateRateClientAppointment(@PathVariable Long idRateAppointment,
                                                  @RequestBody RateAppointmentRequest rateAppointmentRequest){
      log.info("Update rate client appointment: {} {}", idRateAppointment, rateAppointmentRequest);
      return new ResponseEntity<>(this.clientAppointmentBusiness.updateRateAppointment(idRateAppointment, rateAppointmentRequest), HttpStatus.OK);
   }

   @Operation(summary = "Update rate client appointment")
   @GetMapping
   public ResponseEntity<?> getAppointmentsByEmployeeAndDateWithoutStatus(@RequestParam Long storeEmployeeId, @RequestParam LocalDate date, @RequestParam Long excludedStatusId){
      log.info("Get appointments by id store employee, date and without status: {} {} {}", storeEmployeeId, date, excludedStatusId);
      return new ResponseEntity<>(this.clientAppointmentBusiness.getAppointmentsByEmployeeAndDateWithoutStatus(storeEmployeeId, date, excludedStatusId), HttpStatus.OK);
   }

}
