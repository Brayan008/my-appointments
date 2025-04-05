package com.appointment.client.controllers;

import com.appointment.client.business.StoreBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/client/store")
@Tag(name = "Store Controllers", description = "Endpoints to services of stores that can used it by clients.")
public class StoreController {

    private final StoreBusiness storeBusiness;

    @Operation(summary = "Add user favorite store", security = @SecurityRequirement(name = "bearer-jwt"))
    @PostMapping("/{storeId}/favorite-store")
    public ResponseEntity<?> addFavoriteStore(@PathVariable Long storeId,
                                              @Parameter(hidden = true) @RequestHeader("Authorization") String token){
       log.info("Set favorite store {}", storeId);
       return ResponseEntity.ok(this.storeBusiness.addStoreToFavorites(token.replace("Bearer ", ""), storeId));
    }

    @Operation(summary = "Delete user favorite store", security = @SecurityRequirement(name = "bearer-jwt"))
    @DeleteMapping("/favorite-store/{userFavoriteStoreId}")
    public ResponseEntity<?> deleteFavoriteStore(@PathVariable Long userFavoriteStoreId){
        log.info("Delete favorite store {}", userFavoriteStoreId);
        return ResponseEntity.ok(this.storeBusiness.deleteFavoriteStore(userFavoriteStoreId));
    }


   @Operation(summary = "Find stores by search text (name, address or coordinates)", security = @SecurityRequirement(name = "bearer-jwt"))
   @GetMapping("/search")
   public ResponseEntity<?> findStoresBySearchText(@RequestParam(name = "searchText") String searchText){
      return ResponseEntity.ok(this.storeBusiness.findStoresBySearchText(searchText));
   }

   @Operation(summary = "Get employees of store", security = @SecurityRequirement(name = "bearer-jwt"))
   @GetMapping("/{storeId}/employees")
   public ResponseEntity<?> getEmployeesOfStore(@PathVariable Long storeId){
      return ResponseEntity.ok(this.storeBusiness.getEmployeesOfStore(storeId));
   }



}
