package com.appointment.database.controllers;

import com.appointment.database.entities.RoleEntity;
import com.appointment.database.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "role resources", description = "This APi serve all functionality for management roles")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "get roles")
    @GetMapping()
    public ResponseEntity<?> roles(){
        log.info("Get: roles ");
        return ResponseEntity.ok(this.roleService.getRoles());
    }

    @Operation(summary = "get a role given a role id")
    @GetMapping("/{roleId}")
    public ResponseEntity<?> roleById(@PathVariable(name = "roleId") Long roleId){
        log.info("Get: role {}", roleId);
        return ResponseEntity.ok(this.roleService.getRoleById(roleId));
    }

    @Operation(summary = "create a role")
    @PostMapping()
    public ResponseEntity<?> createRole(@RequestBody RoleEntity role, HttpServletRequest request){
        log.info("create: role {}", role.getName());
        RoleEntity newRole = this.roleService.createRole(role);

        String baseUrl = request.getRequestURI();
        URI newLocation = URI.create(baseUrl + "/"+ newRole.getRoleId());

        return ResponseEntity.created(newLocation).body(newRole);
    }

    @Operation(summary = "update a role by role id")
    @PutMapping("/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable(name = "roleId") Long roleId, @RequestBody RoleEntity role){
        log.info("updating: role {}", roleId);
        return ResponseEntity.ok(this.roleService.updateRole(role, roleId));
    }
}
