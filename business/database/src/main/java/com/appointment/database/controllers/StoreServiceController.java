package com.appointment.database.controllers;

import com.appointment.database.entities.StoreServiceEntity;
import com.appointment.database.services.StoreServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "service resources", description = "This APi serve all functionality for management services")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/services")
public class StoreServiceController {
   private final StoreServiceService serviceService;

   @Operation(summary = "get services")
   @GetMapping()
   public ResponseEntity<?> services(){
      log.info("Get: services ");
      return ResponseEntity.ok(this.serviceService.getServices());
   }

   @Operation(summary = "get a service given a service id")
   @GetMapping("/{serviceId}")
   public ResponseEntity<?> serviceById(@PathVariable(name = "serviceId") Long serviceId){
      log.info("Get: serviceId {}", serviceId);
      return ResponseEntity.ok(this.serviceService.getServiceById(serviceId));
   }

   @Operation(summary = "create a service")
   @PostMapping()
   public ResponseEntity<?> createService(@RequestBody StoreServiceEntity serviceEntity, HttpServletRequest request){
      log.info("create: service {}", serviceEntity.getName());
      StoreServiceEntity newService = this.serviceService.createService(serviceEntity);

      String baseUrl = request.getRequestURI();
      URI newLocation = URI.create(baseUrl + "/"+ newService.getStoreServiceId());

      return ResponseEntity.created(newLocation).body(newService);
   }

   @Operation(summary = "update a service by service id")
   @PutMapping("/{serviceId}")
   public ResponseEntity<?> updateService(@PathVariable(name = "serviceId") Long serviceId,
                                          @RequestBody StoreServiceEntity serviceEntity){
      log.info("updating: service {}", serviceEntity.getName());
      return ResponseEntity.ok(this.serviceService.updateService(serviceEntity, serviceId));
   }

   @Operation(summary = "enabled service")
   @PutMapping("/{serviceId}/enabled")
   public ResponseEntity<?> enableService(@PathVariable(name = "serviceId") Long serviceId){
      log.info("enabled service " + serviceId);
      return ResponseEntity.ok(serviceService.enableById(serviceId));
   }

   @Operation(summary = "disabled service")
   @PutMapping("/{serviceId}/disabled")
   public ResponseEntity<?> disableService(@PathVariable(name = "serviceId") Long serviceId){
      log.info("disable service " + serviceId);
      return ResponseEntity.ok(serviceService.disableById(serviceId));
   }

   @Operation(summary = "get services by status id")
   @GetMapping("/status/{statusId}")
   public ResponseEntity<?> findByStatusId(@PathVariable(name = "statusId") Long statusId){
      return ResponseEntity.ok(this.serviceService.findByStatusId(statusId));
   }
}
