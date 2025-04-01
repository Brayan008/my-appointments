package com.appointment.client.controllers;

import com.appointment.client.business.StoreEmployeeBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/client/employees")
@Tag(name = "Employees Controllers", description = "Endpoints to client need about employees.")
public class EmployeeController {

   private final StoreEmployeeBusiness storeEmployeeBusiness;

   @Operation(summary = "Obtain employee availability, check his appointments and config employee schedule")
   @GetMapping("/{storeEmployeeId}/appointments-availability")
   public ResponseEntity<?> getEmployeeAvailability(@PathVariable(name = "storeEmployeeId") Long storeEmployeeId){
      return ResponseEntity.ok(this.storeEmployeeBusiness.getEmployeeAvailability(storeEmployeeId));
   }
}
