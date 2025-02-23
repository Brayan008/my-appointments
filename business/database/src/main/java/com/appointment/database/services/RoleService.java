package com.appointment.database.services;

import com.appointment.database.entities.RoleEntity;

import java.util.List;

public interface RoleService {
    List<RoleEntity> getRoles();

    RoleEntity getRoleById(Long roleId);

    RoleEntity createRole(RoleEntity role);

    RoleEntity updateRole(RoleEntity role, Long roleId);
}
