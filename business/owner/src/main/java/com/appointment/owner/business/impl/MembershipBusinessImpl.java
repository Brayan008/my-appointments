package com.appointment.owner.business.impl;

import com.appointment.commons.dtos.request.MembershipRequest;
import com.appointment.commons.dtos.response.MembershipResponse;
import com.appointment.commons.enums.StatusEnum;
import com.appointment.owner.business.MembershipBusiness;
import com.appointment.owner.services.MembershipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MembershipBusinessImpl implements MembershipBusiness {
    private final MembershipService membershipService;
    @Override
    public List<MembershipResponse> getMemberships() {
        return this.membershipService.getMemberships();
    }

    @Override
    public MembershipResponse getMembershipById(Long membershipId) {
        return this.membershipService.getMembershipById(membershipId);
    }

    @Override
    public MembershipResponse createMembership(MembershipRequest membership) {
        return this.membershipService.createMembership(membership);
    }

    @Override
    public MembershipResponse updateMembership(MembershipRequest membership, Long membershipId) {
        return this.membershipService.updateMembership(membership, membershipId);
    }

    @Override
    public MembershipResponse disableById(Long membershipId) {
        return this.membershipService.disableById(membershipId);
    }

    @Override
    public MembershipResponse enableById(Long membershipId) {
        return this.membershipService.enableById(membershipId);
    }

    @Override
    public List<MembershipResponse> getEnabledMembership() {
        return this.membershipService.findByStatusId(StatusEnum.ENABLED.getCode());
    }

    @Override
    public List<MembershipResponse> getDisabledMembership() {
        return this.membershipService.findByStatusId(StatusEnum.DISABLED.getCode());
    }
}
