package com.appointment.owner.controllers;

import com.appointment.commons.dtos.request.EmployeeRequest;
import com.appointment.commons.dtos.response.EmployeeResponse;
import com.appointment.owner.business.EmployeeBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "employee resources", description = "This APi serve all functionality for management employees")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeBusiness employeeBusiness;

    @Operation(summary = "get employees")
    @GetMapping()
    public ResponseEntity<?> employees(){
        log.info("Get: employees ");
        List<EmployeeResponse> employees = this.employeeBusiness.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "get an employee given a employee id")
    @GetMapping("/{employeeId}")
    public ResponseEntity<?> employeeById(@PathVariable(name = "employeeId") Long employeeId){
        log.info("Get: employee {}", employeeId);
        return ResponseEntity.ok(this.employeeBusiness.getEmployeeById(employeeId));
    }

    @Operation(summary = "create an employee")
    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest,
                                            HttpServletRequest request){
        log.info("create: employee {}", employeeRequest.getUserId());
        EmployeeResponse newEmployee = this.employeeBusiness.createEmployee(employeeRequest);

        String baseUrl = request.getRequestURI();
        URI newLocation = URI.create(baseUrl + "/"+ newEmployee.getEmployeeId());

        return ResponseEntity.created(newLocation).body(newEmployee);
    }

    @Operation(summary = "update an employee by employee id")
    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable(name = "employeeId") Long employeeId,
                                            @RequestBody EmployeeRequest employeeRequest){
        log.info("updating: employee {}", employeeRequest.getUserId());
        return ResponseEntity.ok(this.employeeBusiness.updateEmployee(employeeRequest, employeeId));
    }

    @Operation(summary = "enabled employee")
    @PutMapping("/{employeeId}/enabled")
    public ResponseEntity<?> enableEmployee(@PathVariable(name = "employeeId") Long employeeId){
        log.info("enabled employee " + employeeId);
        return ResponseEntity.ok(employeeBusiness.enableById(employeeId));
    }

    @Operation(summary = "disabled employee")
    @PutMapping("/{employeeId}/disabled")
    public ResponseEntity<?> disableEmployee(@PathVariable(name = "employeeId") Long employeeId){
        log.info("disable employee " + employeeId);
        return ResponseEntity.ok(employeeBusiness.disableById(employeeId));
    }

    @Operation(summary = "get enabled employees")
    @GetMapping("/enabled")
    public ResponseEntity<?> getEnabledEmployees(){
        log.info("Get enabled employees");
        return ResponseEntity.ok(this.employeeBusiness.getEnabledEmployee());
    }

    @Operation(summary = "get disabled employees")
    @GetMapping("/disabled")
    public ResponseEntity<?> getDisabledEmployees(){
        log.info("Get enabled employees");
        return ResponseEntity.ok(this.employeeBusiness.getDisabledEmployee());
    }
}
