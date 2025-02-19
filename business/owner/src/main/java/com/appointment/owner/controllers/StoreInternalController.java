package com.appointment.owner.controllers;

import com.appointment.owner.business.EmployeeBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "store internal resources", description = "This APi serve all functionality for management stores")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/internal/v1/stores")
public class StoreInternalController {

    private final EmployeeBusiness employeeBusiness;

    @Operation(summary = "Get Employees Associated with the Store")
    @GetMapping("/{storeId}/employees")
    public ResponseEntity<?> employeesAssociated(@PathVariable(name = "storeId") Long storeId){
        log.info("enabled store " + storeId);
        return ResponseEntity.ok(employeeBusiness.getEmployeesAssociated(storeId));
    }
}
