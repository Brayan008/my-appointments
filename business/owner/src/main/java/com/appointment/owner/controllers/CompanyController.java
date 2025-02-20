package com.appointment.owner.controllers;

import com.appointment.commons.dtos.request.CompanyRequest;
import com.appointment.commons.dtos.response.CompanyResponse;
import com.appointment.owner.business.CompanyBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "company resources", description = "This APi serve all functionality for management company")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyBusiness companyBusiness;

    @Operation(summary = "get all companies")
    @GetMapping("/companies")
    public ResponseEntity<?> companies(){
        log.info("Get: companies ");
        List<CompanyResponse> companies = this.companyBusiness.getCompanies();
        return ResponseEntity.ok(companies);
    }

    @Operation(summary = "get a company given a company id")
    @GetMapping("/{companyId}")
    public ResponseEntity<?> companyById(@PathVariable(name = "companyId") Long companyId){
        log.info("Get: company {}", companyId);
        return ResponseEntity.ok(this.companyBusiness.getCompanyById(companyId));
    }

    @Operation(summary = "create a company")
    @PostMapping()
    public ResponseEntity<?> company(@RequestBody CompanyRequest companyRequest){
        log.info("create: company {}", companyRequest.getName());
        CompanyResponse newCompany = this.companyBusiness.createCompany(companyRequest);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @Operation(summary = "update a company by company id")
    @PutMapping("/{companyId}")
    public ResponseEntity<?> updateCompany(@RequestBody CompanyRequest companyRequest,
                                           @PathVariable(name = "companyId") Long companyId){
        log.info("updating: company {}", companyRequest.getName());
        return ResponseEntity.ok(this.companyBusiness.updateCompany(companyRequest, companyId));
    }

    @Operation(summary = "enabled company")
    @PutMapping("/{companyId}/enabled")
    public ResponseEntity<?> enableCompany(@PathVariable(name = "companyId") Long companyId){
        log.info("enabled company " + companyId);
        return ResponseEntity.ok(companyBusiness.enableById(companyId));
    }

    @Operation(summary = "disabled company")
    @PutMapping("/{companyId}/disabled")
    public ResponseEntity<?> disableCompany(@PathVariable(name = "companyId") Long companyId){
        log.info("disable company " + companyId);
        return ResponseEntity.ok(companyBusiness.disableById(companyId));
    }

    @Operation(summary = "get enabled companies")
    @GetMapping("/enabled")
    public ResponseEntity<?> getEnabledCompany(){
        log.info("Get enabled companies");
        return ResponseEntity.ok(this.companyBusiness.getEnabledCompany());
    }

    @Operation(summary = "get disabled companies")
    @GetMapping("/disabled")
    public ResponseEntity<?> getDisabledCompany(){
        log.info("Get enabled companies");
        return ResponseEntity.ok(this.companyBusiness.getDisabledCompany());
    }
}
