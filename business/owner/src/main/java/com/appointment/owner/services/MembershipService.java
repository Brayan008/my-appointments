package com.appointment.owner.services;

import com.appointment.commons.dtos.request.MembershipRequest;
import com.appointment.commons.dtos.response.MembershipResponse;

import java.util.List;

public interface MembershipService {
    List<MembershipResponse> getMemberships();

    MembershipResponse getMembershipById(Long membershipId);

    MembershipResponse createMembership(MembershipRequest membership);

    MembershipResponse updateMembership(MembershipRequest membership, Long membershipId);

    MembershipResponse disableById(Long membershipId);

    MembershipResponse enableById(Long membershipId);

    List<MembershipResponse> findByStatusId(Long statusId);
}
