package com.appointment.gatewayserver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
public class TestController {

   @Operation(summary = "Probar API", description = "Endpoint de prueba para verificar la disponibilidad del API.", security = @SecurityRequirement(name = "bearer-jwt"))
   @GetMapping("/test")
   public ResponseEntity<String> testEndpoint() {
      return ResponseEntity.ok("API is working!");
   }

   @Operation(summary = "Probar API para admins", description = "Endpoint de prueba para verificar que el rol admin funciona.", security = @SecurityRequirement(name = "bearer-jwt"))
   @GetMapping("/test-admin")
   public ResponseEntity<String> testAdminRole() {
      return ResponseEntity.ok("API is working! - HI ADMIN");
   }
}
