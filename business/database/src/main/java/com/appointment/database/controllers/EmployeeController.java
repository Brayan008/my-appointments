package com.appointment.database.controllers;

import com.appointment.database.entities.EmployeeEntity;
import com.appointment.database.services.EmployeeService;
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

    private final EmployeeService employeeService;

    @Operation(summary = "get employees")
    @GetMapping()
    public ResponseEntity<?> employees(){
        return ResponseEntity.ok(this.employeeService.getEmployees());
    }

    @Operation(summary = "get an employee given a employee id")
    @GetMapping("/{employeeId}")
    public ResponseEntity<?> employeeById(@PathVariable(name = "employeeId") Long employeeId){
        log.info("Get: employee {}", employeeId);
        return ResponseEntity.ok(this.employeeService.getEmployeeById(employeeId));
    }

    @Operation(summary = "create an employee")
    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeEntity employeeRequest,
                                            HttpServletRequest request){
        log.info("create: employee {}", employeeRequest.getUserId());

        return ResponseEntity.ok().body(this.employeeService.createEmployee(employeeRequest));
    }

    @Operation(summary = "update an employee by employee id")
    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable(name = "employeeId") Long employeeId,
                                            @RequestBody EmployeeEntity employeeRequest){
        log.info("updating: employee {}", employeeRequest.getUserId());
        return ResponseEntity.ok(this.employeeService.updateEmployee(employeeRequest, employeeId));
    }

    @Operation(summary = "enabled employee")
    @PutMapping("/{employeeId}/enabled")
    public ResponseEntity<?> enableEmployee(@PathVariable(name = "employeeId") Long employeeId){
        log.info("enabled employee " + employeeId);
        return ResponseEntity.ok(employeeService.enableById(employeeId));
    }

    @Operation(summary = "disabled employee")
    @PutMapping("/{employeeId}/disabled")
    public ResponseEntity<?> disableEmployee(@PathVariable(name = "employeeId") Long employeeId){
        log.info("disable employee " + employeeId);
        return ResponseEntity.ok(employeeService.disableById(employeeId));
    }

    @Operation(summary = "get employees by status id")
    @GetMapping("/status/{statusId}")
    public ResponseEntity<?> getEnabledEmployees(@PathVariable(name = "statusId") Long statusId){
        return ResponseEntity.ok(this.employeeService.findByStatusId(statusId));
    }

}
