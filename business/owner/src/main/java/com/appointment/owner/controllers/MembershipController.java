package com.appointment.owner.controllers;

import com.appointment.commons.dtos.request.MembershipRequest;
import com.appointment.commons.dtos.response.MembershipResponse;
import com.appointment.owner.business.MembershipBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "membership resources", description = "This APi serve all functionality for management memberships")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/memberships")
public class MembershipController {
    @Autowired
    private MembershipBusiness membershipBusiness;

    @Operation(summary = "get all memberships")
    @GetMapping()
    public ResponseEntity<?> memberships(){
        log.info("Get: memberships ");
        List<MembershipResponse> memberships = this.membershipBusiness.getMemberships();
        return ResponseEntity.ok(memberships);
    }

    @Operation(summary = "get a membership given a membership id")
    @GetMapping("/{membershipId}")
    public ResponseEntity<?> membershipById(@PathVariable(name = "membershipId") Long membershipId){
        log.info("Get: membership {}", membershipId);
        return ResponseEntity.ok(this.membershipBusiness.getMembershipById(membershipId));
    }

    @Operation(summary = "create a membership")
    @PostMapping()
    public ResponseEntity<?> createMembership(@Valid @RequestBody MembershipRequest membershipRequest,
                                              HttpServletRequest request){
        log.info("create: membership {}", membershipRequest.getDescription());
        MembershipResponse newMembership = this.membershipBusiness.createMembership(membershipRequest);

        String baseUrl = request.getRequestURI();
        URI newLocation = URI.create(baseUrl + "/"+ newMembership.getMembershipId());

        return ResponseEntity.created(newLocation).body(newMembership);
    }

    @Operation(summary = "update a membership by membership id")
    @PutMapping("/{membershipId}")
    public ResponseEntity<?> updateMembership(@PathVariable(name = "membershipId") Long membershipId,
                                              @Valid @RequestBody MembershipRequest membershipRequest){
        log.info("updating: membership {}", membershipRequest.getDescription());
        return ResponseEntity.ok(this.membershipBusiness.updateMembership(membershipRequest, membershipId));
    }

    @Operation(summary = "enabled membership")
    @PutMapping("/{membershipId}/enabled")
    public ResponseEntity<?> enableMembership(@PathVariable(name = "membershipId") Long membershipId){
        log.info("enabled membership " + membershipId);
        return ResponseEntity.ok(membershipBusiness.enableById(membershipId));
    }

    @Operation(summary = "disabled membership")
    @PutMapping("/{membershipId}/disabled")
    public ResponseEntity<?> disableMembership(@PathVariable(name = "membershipId") Long membershipId){
        log.info("disable membership " + membershipId);
        return ResponseEntity.ok(membershipBusiness.disableById(membershipId));
    }

    @Operation(summary = "get enabled memberships")
    @GetMapping("/enabled")
    public ResponseEntity<?> getEnabledMemberships(){
        log.info("Get enabled memberships");
        return ResponseEntity.ok(this.membershipBusiness.getEnabledMembership());
    }

    @Operation(summary = "get disabled memberships")
    @GetMapping("/disabled")
    public ResponseEntity<?> getDisabledMemberships(){
        log.info("Get enabled memberships");
        return ResponseEntity.ok(this.membershipBusiness.getDisabledMembership());
    }
}
