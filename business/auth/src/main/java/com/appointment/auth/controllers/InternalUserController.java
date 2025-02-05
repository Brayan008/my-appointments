package com.appointment.auth.controllers;

import com.appointment.auth.business.UserBusiness;
import com.appointment.auth.dto.UserInfoDTORes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/internal/auth")
@Tag(name = "Internal User Controller", description = "Endpoints to access internal users endpoints.")
public class InternalUserController {

    private UserBusiness userBusiness;

    @Operation(
            summary = "Obtener ID Token",
            description = "Realiza una solicitud con tu accessToken para obtener la informacion del usuario."
    )
    @PostMapping("/getUserInfo")
    public Mono<UserInfoDTORes> getUserInfo(
            @Parameter(description = "Access token obtenido previamente en el endpoint de token")
            @RequestParam(required = false) String accessToken,
            @RequestParam(required = false) String email) {
        return userBusiness.getUserInfo(accessToken, email);
    }

    @Operation(summary = "Probar API", description = "Endpoint de prueba para verificar la disponibilidad del API.")
    @GetMapping("/test")
    public Mono<ResponseEntity<String>> testEndpoint() {
        return Mono.just(ResponseEntity.ok("API is working!"));
    }

}
