package com.appointment.database.services.impl;

import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.OwnerEntity;
import com.appointment.database.repositories.OwnerRepository;
import com.appointment.database.services.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public List<OwnerEntity> getOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public OwnerEntity getOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId)
            .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
                "owner not found with id " + ownerId, HttpStatus.NOT_FOUND));
    }

    @Override
    public OwnerEntity createOwner(OwnerEntity owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public OwnerEntity updateOwner(OwnerEntity owner, Long ownerId) {
        OwnerEntity currentOwner = this.getOwnerById(ownerId);
        currentOwner.setUserId(owner.getUserId());
        currentOwner.setCompanyId(owner.getCompanyId());
        currentOwner.setOwnerHierarchy(owner.getOwnerHierarchy());
        return this.createOwner(currentOwner);
    }
}
