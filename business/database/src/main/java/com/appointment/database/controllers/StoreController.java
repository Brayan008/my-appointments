package com.appointment.database.controllers;

import com.appointment.database.business.UserFavoriteStoreBusiness;
import com.appointment.database.entities.StoreEntity;
import com.appointment.database.services.StoreEmployeeService;
import com.appointment.database.services.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "store resources", description = "This APi serve all functionality for management stores")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/stores")
public class StoreController {
   private final StoreService storeService;
   private final StoreEmployeeService storeEmployeeService;
   private final UserFavoriteStoreBusiness userFavoriteStoreBusiness;

   @Operation(summary = "get stores")
   @GetMapping()
   public ResponseEntity<?> stores(){
      log.info("Get: stores ");
      return ResponseEntity.ok(this.storeService.getStores());
   }

   @Operation(summary = "get a store given a store id")
   @GetMapping("/{storeId}")
   public ResponseEntity<?> storeById(@PathVariable(name = "storeId") Long storeId){
      log.info("Get: storeId {}", storeId);
      return ResponseEntity.ok(this.storeService.getStoreById(storeId));
   }

   @Operation(summary = "create a store")
   @PostMapping()
   public ResponseEntity<?> createStore(@RequestBody StoreEntity storeEntity,
                                        HttpServletRequest request){
      log.info("create: store {}", storeEntity.getName());
      StoreEntity newStore = this.storeService.createStore(storeEntity);

      String baseUrl = request.getRequestURI();
      URI newLocation = URI.create(baseUrl + "/"+ newStore.getStoreId());

      return ResponseEntity.created(newLocation).body(newStore);
   }

   @Operation(summary = "update a store by store id")
   @PutMapping("/{storeId}")
   public ResponseEntity<?> updateStore(@PathVariable(name = "storeId") Long storeId,
                                        @RequestBody StoreEntity storeEntity){
      log.info("updating: store {}", storeId);
      return ResponseEntity.ok(this.storeService.updateStore(storeEntity, storeId));
   }

   @Operation(summary = "enabled store")
   @PutMapping("/{storeId}/enabled")
   public ResponseEntity<?> enableStore(@PathVariable(name = "storeId") Long storeId){
      log.info("enabled store " + storeId);
      return ResponseEntity.ok(storeService.enableById(storeId));
   }

   @Operation(summary = "disabled store")
   @PutMapping("/{storeId}/disabled")
   public ResponseEntity<?> disableStore(@PathVariable(name = "storeId") Long storeId){
      log.info("disable store " + storeId);
      return ResponseEntity.ok(storeService.disableById(storeId));
   }

   @Operation(summary = "get store by statusId id")
   @GetMapping("/status/{statusId}")
   public ResponseEntity<?> findByStatusId(@PathVariable(name = "statusId") Long statusId){
      return ResponseEntity.ok(this.storeService.findByStatusId(statusId));
   }

   @Operation(summary = "Get Employees Associated with the Store")
   @GetMapping("/{storeId}/employees")
   public ResponseEntity<?> employeesAssociated(@PathVariable(name = "storeId") Long storeId){
      log.info("enabled store " + storeId);
      return ResponseEntity.ok(storeEmployeeService.getEmployeesAssociated(storeId));
   }

   @Operation(summary = "Find stores by search text (name, address or coordinates)")
   @GetMapping("/search")
   public ResponseEntity<?> findStoresBySearchText(@RequestParam(name = "searchText") String searchText){
      return ResponseEntity.ok(storeService.findStoresBySearchText(searchText));
   }

   @Operation(summary = "Add user favorite store")
   @PostMapping("/{storeId}/favorite-store/user/{email}")
   public ResponseEntity<?> addFavoriteStore(@PathVariable Long storeId,
                                             @PathVariable String email){
      log.info("Set favorite store {}", storeId);
      return ResponseEntity.ok(this.userFavoriteStoreBusiness.addFavoriteStore(storeId, email));
   }

   @Operation(summary = "Delete user favorite store")
   @DeleteMapping("/favorite-store/{userFavoriteStoreId}")
   public ResponseEntity<?> deleteFavoriteStore(@PathVariable Long userFavoriteStoreId){
      log.info("Delete favorite store {}", userFavoriteStoreId);
      return ResponseEntity.ok(this.userFavoriteStoreBusiness.deleteFavoriteStore(userFavoriteStoreId));
   }
}
