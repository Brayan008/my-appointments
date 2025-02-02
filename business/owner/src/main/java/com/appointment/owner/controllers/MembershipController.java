package com.appointment.owner.controllers;

import com.appointment.owner.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;
}
