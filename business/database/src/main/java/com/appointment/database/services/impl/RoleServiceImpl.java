package com.appointment.database.services.impl;

import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.RoleEntity;
import com.appointment.database.repositories.RoleRepository;
import com.appointment.database.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleEntity> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity getRoleById(Long roleId) {
        return roleRepository.findById(roleId)
            .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
                "role not found with id " + roleId, HttpStatus.NOT_FOUND));
    }

    @Override
    public RoleEntity createRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity updateRole(RoleEntity role, Long roleId) {
        RoleEntity currentRole = this.getRoleById(roleId);
        currentRole.setName(role.getName());
        return this.createRole(currentRole);
    }
}
