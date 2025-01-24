package com.appointment.gatewayserver;

import com.appointment.gatewayserver.dto.AuthTokenResponse;
import com.appointment.gatewayserver.dto.UserAuthResDTO;
import com.appointment.gatewayserver.service.Auth0Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@AllArgsConstructor
public class OAuth2Controller {

    private Auth0Service auth0Service;

    @Operation(
            summary = "Obtener ID Token",
            description = "Realiza una solicitud a Auth0 para obtener el ID Token usando el código de autorización."
    )
    @PostMapping("/token")
    public Mono<AuthTokenResponse> getIdToken(
            @Parameter(description = "Authorization Code obtenido previamente en el flujo OAuth2", required = true)
            @RequestParam String code) {
        return auth0Service.getApiToken(code, "http://localhost:8080");
    }

    @Operation(
            summary = "Obtener ID Token",
            description = "Realiza una solicitud con tu accessToken para obtener la informacion del usuario.",
            security = @SecurityRequirement(name = "bearer-jwt")
    )
    @PostMapping("/getUserInfo")
    public Mono<UserAuthResDTO> getUserInfo(
            @Parameter(description = "Access token obtenido previamente en el endpoint de token", required = true)
            @RequestParam String accessToken) {
        return auth0Service.getUserInfo(accessToken);
    }

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
