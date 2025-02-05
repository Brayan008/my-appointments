package com.appointment.auth.controllers;

import com.appointment.auth.dto.UserDTOReq;
import com.appointment.auth.dto.UserDTORes;
import com.appointment.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/auth/user")
@Tag(name = "Users Controller", description = "Endpoints management users.")
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "Validations login user",
        description = "This endpoint is run from auth0 for internal user validations"
    )
    @PostMapping("/validate-login")
    public Mono<UserDTORes> validateLoginUser(@RequestBody UserDTOReq userDTOReq) {
        return userService.validateLoginUser(userDTOReq);
    }


}
