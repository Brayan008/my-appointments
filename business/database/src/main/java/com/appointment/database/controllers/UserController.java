package com.appointment.database.controllers;

import com.appointment.database.entities.UserEntity;
import com.appointment.database.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "user resources", description = "This APi serve all functionality for management users")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "get users")
    @GetMapping()
    public ResponseEntity<?> users(){
        log.info("Get: users ");
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @Operation(summary = "get a user given a user id")
    @GetMapping("/{userId}")
    public ResponseEntity<?> userById(@PathVariable(name = "userId") Long userId){
        log.info("Get: user {}", userId);
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @Operation(summary = "create a user")
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserEntity user, HttpServletRequest request){
        log.info("create: user {}", user.getEmail());
        UserEntity newUser = this.userService.createUser(user);

        String baseUrl = request.getRequestURI();
        URI newLocation = URI.create(baseUrl + "/"+ newUser.getUserId());

        return ResponseEntity.created(newLocation).body(newUser);
    }

    @Operation(summary = "update a user by user id")
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "userId") Long userId,
                                        @RequestBody UserEntity user){
        log.info("updating: user {}", user);
        return ResponseEntity.ok(this.userService.updateUser(user, userId));
    }

    @Operation(summary = "enabled user")
    @PutMapping("/{userId}/enabled")
    public ResponseEntity<?> enableUser(@PathVariable(name = "userId") Long userId){
        log.info("enabled user " + userId);
        return ResponseEntity.ok(userService.enableById(userId));
    }

    @Operation(summary = "disabled user")
    @PutMapping("/{userId}/disabled")
    public ResponseEntity<?> disableUser(@PathVariable(name = "userId") Long userId){
        log.info("disable user " + userId);
        return ResponseEntity.ok(userService.disableById(userId));
    }

    @Operation(summary = "get user by status id")
    @GetMapping("/status/{statusId}")
    public ResponseEntity<?> findByStatusId(@PathVariable(name = "statusId") Long statusId){
        return ResponseEntity.ok(this.userService.findByStatusId(statusId));
    }
}
