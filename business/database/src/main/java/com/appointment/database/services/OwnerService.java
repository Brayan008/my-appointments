package com.appointment.database.services;

import com.appointment.database.entities.OwnerEntity;

import java.util.List;

public interface OwnerService {
    List<OwnerEntity> getOwners();

    OwnerEntity getOwnerById(Long ownerId);

    OwnerEntity createOwner(OwnerEntity owner);

    OwnerEntity updateOwner(OwnerEntity owner, Long ownerId);
}
