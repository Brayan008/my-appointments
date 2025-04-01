package com.appointment.database.controllers;

import com.appointment.database.business.ConfigEmployeeScheduleBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Config Employee Schedule", description = "This APi serve all functionality for management config employees")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/config-employee-schedule")
public class ConfigEmployeeScheduleController {

   private final ConfigEmployeeScheduleBusiness configEmployeeScheduleBusiness;

   @Operation(summary = "Get config employee ")
   @GetMapping()
   public ResponseEntity<?> findByStoreEmployeeIdAndDayOfWeek(@RequestParam Long storeEmployeeId, @RequestParam Integer dayOfWeek){
      log.info("Get config employee by storeEmployeeId and dayOfWeek {} {}", storeEmployeeId, dayOfWeek);
      return ResponseEntity.ok(this.configEmployeeScheduleBusiness.findByStoreEmployeeIdAndDayOfWeek(storeEmployeeId, dayOfWeek));
   }


}
