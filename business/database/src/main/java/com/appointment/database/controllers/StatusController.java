package com.appointment.database.controllers;

import com.appointment.database.entities.StatusEntity;
import com.appointment.database.services.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "status resources", description = "This APi serve all functionality for management status")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/status")
public class StatusController {
   private final StatusService statusService;

   @Operation(summary = "get status")
   @GetMapping()
   public ResponseEntity<?> status(){
      log.info("Get: status ");
      return ResponseEntity.ok(this.statusService.getStatus());
   }

   @Operation(summary = "get a status given a status id")
   @GetMapping("/{statusId}")
   public ResponseEntity<?> statusById(@PathVariable(name = "statusId") Long statusId){
      log.info("Get: status {}", statusId);
      return ResponseEntity.ok(this.statusService.getStatusById(statusId));
   }

   @Operation(summary = "create a status")
   @PostMapping()
   public ResponseEntity<?> createStatus(@RequestBody StatusEntity status, HttpServletRequest request){
      log.info("create: status {}", status.getName());
      StatusEntity newStatus = this.statusService.createStatus(status);

      String baseUrl = request.getRequestURI();
      URI newLocation = URI.create(baseUrl + "/"+ newStatus.getStatusId());

      return ResponseEntity.created(newLocation).body(newStatus);
   }

   @Operation(summary = "update a status by status id")
   @PutMapping("/{statusId}")
   public ResponseEntity<?> updateStatus(@PathVariable(name = "statusId") Long statusId,
                                         @RequestBody StatusEntity status){
      log.info("updating: status {}", statusId);
      return ResponseEntity.ok(this.statusService.updateStatus(status, statusId));
   }
}
