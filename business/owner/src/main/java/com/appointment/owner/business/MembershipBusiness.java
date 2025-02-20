package com.appointment.owner.business;

import com.appointment.commons.dtos.request.MembershipRequest;
import com.appointment.commons.dtos.response.MembershipResponse;

import java.util.List;

public interface MembershipBusiness {
    List<MembershipResponse> getMemberships();

    MembershipResponse getMembershipById(Long membershipId);

    MembershipResponse createMembership(MembershipRequest membership);

    MembershipResponse updateMembership(MembershipRequest membership, Long membershipId);

    MembershipResponse disableById(Long membershipId);

    MembershipResponse enableById(Long membershipId);

    List<MembershipResponse> getEnabledMembership();

    List<MembershipResponse> getDisabledMembership();
}
