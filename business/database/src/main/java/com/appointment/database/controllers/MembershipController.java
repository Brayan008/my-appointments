package com.appointment.database.controllers;

import com.appointment.database.entities.MembershipEntity;
import com.appointment.database.services.MembershipService;
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

@Tag(name = "membership resources", description = "This APi serve all functionality for management memberships")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/memberships")
public class MembershipController {
   private final MembershipService membershipService;

   @Operation(summary = "get all memberships")
   @GetMapping()
   public ResponseEntity<?> memberships(){
      log.info("Get: memberships ");
      return ResponseEntity.ok(this.membershipService.getMemberships());
   }

   @Operation(summary = "get a membership given a membership id")
   @GetMapping("/{membershipId}")
   public ResponseEntity<?> membershipById(@PathVariable(name = "membershipId") Long membershipId){
      log.info("Get: membership {}", membershipId);
      return ResponseEntity.ok(this.membershipService.getMembershipById(membershipId));
   }

   @Operation(summary = "create a membership")
   @PostMapping()
   public ResponseEntity<?> createMembership(@Valid @RequestBody MembershipEntity membershipRequest,
                                             HttpServletRequest request){
      log.info("create: membership {}", membershipRequest.getDescription());
      MembershipEntity newMembership = this.membershipService.createMembership(membershipRequest);

      String baseUrl = request.getRequestURI();
      URI newLocation = URI.create(baseUrl + "/"+ newMembership.getMembershipId());

      return ResponseEntity.created(newLocation).body(newMembership);
   }

   @Operation(summary = "update a membership by membership id")
   @PutMapping("/{membershipId}")
   public ResponseEntity<?> updateMembership(@PathVariable(name = "membershipId") Long membershipId,
                                             @Valid @RequestBody MembershipEntity membershipRequest){
      log.info("updating: membership {}", membershipRequest.getDescription());
      return ResponseEntity.ok(this.membershipService.updateMembership(membershipRequest, membershipId));
   }

   @Operation(summary = "enabled membership")
   @PutMapping("/{membershipId}/enabled")
   public ResponseEntity<?> enableMembership(@PathVariable(name = "membershipId") Long membershipId){
      log.info("enabled membership " + membershipId);
      return ResponseEntity.ok(membershipService.enableById(membershipId));
   }

   @Operation(summary = "disabled membership")
   @PutMapping("/{membershipId}/disabled")
   public ResponseEntity<?> disableMembership(@PathVariable(name = "membershipId") Long membershipId){
      log.info("disable membership " + membershipId);
      return ResponseEntity.ok(membershipService.disableById(membershipId));
   }

   @Operation(summary = "get membership by status id")
   @GetMapping("/status/{statusId}")
   public ResponseEntity<?> findByStatusId(@PathVariable(name = "statusId") Long statusId){
      return ResponseEntity.ok(this.membershipService.findByStatusId(statusId));
   }
}
