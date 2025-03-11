package com.appointment.owner.business;


import com.appointment.commons.dtos.request.OwnerRequest;
import com.appointment.commons.dtos.response.OwnerResponse;

import java.util.List;

public interface OwnerBusiness {
    List<OwnerResponse> getOwners();

    OwnerResponse getOwnerById(Long ownerId);

    OwnerResponse createOwner(OwnerRequest owner);

    OwnerResponse updateOwner(OwnerRequest owner, Long ownerId);
}
