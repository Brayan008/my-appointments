package com.appointment.database.controllers;

import com.appointment.commons.dtos.request.ClientDateRequest;
import com.appointment.database.business.ClientDatesBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "company resources", description = "This APi serve all functionality for management company")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/client-dates")
public class ClientDatesController {

    private final ClientDatesBusiness clientDatesBusiness;

    @Operation(summary = "Create a date to client")
    @PostMapping()
    public ResponseEntity<?> createClientDate(@RequestBody ClientDateRequest clientDateRequest){
        log.info("create: client date {}", clientDateRequest);
        return new ResponseEntity<>(this.clientDatesBusiness.createClientDate(clientDateRequest), HttpStatus.CREATED);
    }


}
