package com.appointment.database.controllers;

import com.appointment.database.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private Environment env;

    @GetMapping("/check")
    public String check(){
        return "Your environment is " + env.getProperty("environment") + " with the database "+ env.getProperty("db");
    }
}
