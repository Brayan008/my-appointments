package com.appointment.database.controllers;

import com.appointment.database.entities.OwnerEntity;
import com.appointment.database.services.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "owner resources", description = "This APi serve all functionality for management owners")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/owners")
public class OwnerController {
   private final Environment env;
   private final OwnerService ownerService;

   @Operation(summary = "get owners")
   @GetMapping()
   public ResponseEntity<?> owners(){
      log.info("Get: owners ");
      return ResponseEntity.ok(this.ownerService.getOwners());
   }

   @Operation(summary = "get a owner given a owner id")
   @GetMapping("/{ownerId}")
   public ResponseEntity<?> ownerById(@PathVariable(name = "ownerId") Long ownerId){
      log.info("Get: owner {}", ownerId);
      return ResponseEntity.ok(this.ownerService.getOwnerById(ownerId));
   }

   @Operation(summary = "create a owner")
   @PostMapping()
   public ResponseEntity<?> createOwner(@RequestBody OwnerEntity owner, HttpServletRequest request){
      log.info("create: owner {}", owner.getUserId());
      OwnerEntity newOwner = this.ownerService.createOwner(owner);

      String baseUrl = request.getRequestURI();
      URI newLocation = URI.create(baseUrl + "/"+ newOwner.getOwnerId());

      return ResponseEntity.created(newLocation).body(newOwner);
   }

   @Operation(summary = "update a owner by owner id")
   @PutMapping("/{ownerId}")
   public ResponseEntity<?> updateOwner(@PathVariable(name = "ownerId") Long ownerId,
                                        @RequestBody OwnerEntity owner){
      log.info("updating: owner {}", ownerId);
      return ResponseEntity.ok(this.ownerService.updateOwner(owner, ownerId));
   }

   @GetMapping("/check")
   public String check(){
      return "Your environment is " + env.getProperty("environment") + " with the database "+ env.getProperty("db");
   }
}
