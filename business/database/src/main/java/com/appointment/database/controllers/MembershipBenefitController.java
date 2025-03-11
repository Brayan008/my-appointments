package com.appointment.database.controllers;

import com.appointment.database.entities.MembershipBenefit;
import com.appointment.database.services.MembershipBenefitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "membership benefits resources", description = "This APi serve all functionality for management benefits")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/membership/benefits")
public class MembershipBenefitController {

    private final MembershipBenefitService membershipBenefitService;

    @Operation(summary = "get membership benefits")
    @GetMapping()
    public ResponseEntity<?> benefits(){
        log.info("Get: benefits ");
        return ResponseEntity.ok(this.membershipBenefitService.getBenefits());
    }

    @Operation(summary = "get a membership benefit given a benefit id")
    @GetMapping("/{benefitId}")
    public ResponseEntity<?> benefitById(@PathVariable(name = "benefitId") Long benefitId){
        log.info("Get: benefit {}", benefitId);
        return ResponseEntity.ok(this.membershipBenefitService.getBenefitById(benefitId));
    }

    @Operation(summary = "create a membership benefit")
    @PostMapping()
    public ResponseEntity<?> createBenefit(@Valid @RequestBody MembershipBenefit membershipBenefit,
                                           HttpServletRequest request){
        log.info("create: membership benefit {}", membershipBenefit.getBenefit());
        MembershipBenefit newBenefit = this.membershipBenefitService.createBenefit(membershipBenefit);

        String baseUrl = request.getRequestURI();
        URI newLocation = URI.create(baseUrl + "/"+ newBenefit.getMembershipBenefitId());

        return ResponseEntity.created(newLocation).body(newBenefit);
    }

    @Operation(summary = "update a membership benefit by benefit id")
    @PutMapping("/{benefitId}")
    public ResponseEntity<?> updateBenefit(@PathVariable(name = "benefitId") Long benefitId,
                                           @Valid @RequestBody MembershipBenefit membershipBenefit){
        log.info("updating: membershipBenefit {}", membershipBenefit.getBenefit());
        return ResponseEntity.ok(this.membershipBenefitService.updateBenefit(membershipBenefit, benefitId));
    }
}
