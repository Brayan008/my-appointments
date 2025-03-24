package com.appointment.database.controllers;

import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.services.StoreEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "employee resources", description = "This APi serve all functionality for management employees")
@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
   private final StoreEmployeeService storeEmployeeService;

   @Operation(summary = "get employees")
   @GetMapping()
   public ResponseEntity<?> employees(){
      return ResponseEntity.ok(this.storeEmployeeService.getEmployees());
   }

   @Operation(summary = "get an employee given a store employee id")
   @GetMapping("/{employeeId}")
   public ResponseEntity<?> employeeById(@PathVariable(name = "employeeId") Long employeeId){
      log.info("Get: employee {}", employeeId);
      return ResponseEntity.ok(this.storeEmployeeService.getStoreEmployeeById(employeeId));
   }

   @Operation(summary = "create an employee")
   @PostMapping()
   public ResponseEntity<?> createEmployee(@RequestBody StoreEmployeeEntity employeeRequest,
                                           HttpServletRequest request){
      log.info("create: employee {}", employeeRequest.getUserId());
      return ResponseEntity.ok().body(this.storeEmployeeService.createEmployee(employeeRequest));
   }

   @Operation(summary = "update an employee by employee id")
   @PutMapping("/{employeeId}")
   public ResponseEntity<?> updateEmployee(@PathVariable(name = "employeeId") Long employeeId,
                                           @RequestBody StoreEmployeeEntity employeeRequest){
      log.info("updating: employee {}", employeeRequest.getUserId());
      return ResponseEntity.ok(this.storeEmployeeService.updateEmployee(employeeRequest, employeeId));
   }

   @Operation(summary = "enabled employee")
   @PutMapping("/{employeeId}/enabled")
   public ResponseEntity<?> enableEmployee(@PathVariable(name = "employeeId") Long employeeId){
      log.info("enabled employee " + employeeId);
      return ResponseEntity.ok(storeEmployeeService.enableById(employeeId));
   }

   @Operation(summary = "disabled employee")
   @PutMapping("/{employeeId}/disabled")
   public ResponseEntity<?> disableEmployee(@PathVariable(name = "employeeId") Long employeeId){
      log.info("disable employee " + employeeId);
      return ResponseEntity.ok(storeEmployeeService.disableById(employeeId));
   }

   @Operation(summary = "get employees by status id")
   @GetMapping("/status/{statusId}")
   public ResponseEntity<?> getEnabledEmployees(@PathVariable(name = "statusId") Long statusId){
      return ResponseEntity.ok(this.storeEmployeeService.findByStatusId(statusId));
   }
}
