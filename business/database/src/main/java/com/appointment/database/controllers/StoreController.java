package com.appointment.database.controllers;

import com.appointment.database.services.EmployeeService;
import com.appointment.database.services.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@Slf4j
public class StoreController {
    private final StoreService storeService;
    private final EmployeeService employeeService;

    @Operation(summary = "Get Employees Associated with the Store")
    @GetMapping("/{storeId}/employees")
    public ResponseEntity<?> employeesAssociated(@PathVariable(name = "storeId") Long storeId){
        log.info("enabled store " + storeId);
        return ResponseEntity.ok(employeeService.getEmployeesAssociated(storeId));
    }
}
