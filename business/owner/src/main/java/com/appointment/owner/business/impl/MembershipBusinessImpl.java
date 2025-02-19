package com.appointment.owner.business.impl;

import com.appointment.commons.enums.Status;
import com.appointment.owner.business.MembershipBusiness;
import com.appointment.owner.commons.mappers.MembershipRequestMapper;
import com.appointment.owner.commons.mappers.MembershipResponseMapper;
import com.appointment.owner.dtos.request.MembershipRequest;
import com.appointment.owner.dtos.response.MembershipResponse;
import com.appointment.owner.entities.MembershipEntity;
import com.appointment.owner.services.MembershipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MembershipBusinessImpl implements MembershipBusiness {

    private final MembershipService membershipService;

    @Autowired
    private MembershipResponseMapper membershipResponseMapper;

    @Autowired
    private MembershipRequestMapper membershipRequestMapper;

    @Override
    public List<MembershipResponse> getMemberships() {
        return membershipResponseMapper.membershipEntitiesToMembershipResponses(
            this.membershipService.getMemberships()
        );
    }

    @Override
    public MembershipResponse getMembershipById(Long membershipId) {
        return membershipResponseMapper.membershipEntityToMembershipResponse(
            this.membershipService.getMembershipById(membershipId)
        );
    }

    @Override
    public MembershipResponse createMembership(MembershipRequest membership) {
        MembershipEntity currentMembership = membershipRequestMapper.membershipRequestToMembershipEntity(membership);
        MembershipEntity newMembership = this.membershipService.createMembership(currentMembership);

        return this.membershipResponseMapper.membershipEntityToMembershipResponse(newMembership);
    }

    @Override
    public MembershipResponse updateMembership(MembershipRequest membership, Long membershipId) {
        MembershipEntity currentMembership = membershipRequestMapper.membershipRequestToMembershipEntity(membership);
        MembershipEntity membershipUpdated = this.membershipService.updateMembership(currentMembership, membershipId);
        return this.membershipResponseMapper.membershipEntityToMembershipResponse(membershipUpdated);
    }

    @Override
    public MembershipResponse disableById(Long membershipId) {
        MembershipEntity membership = membershipService.disableById(membershipId);
        return this.membershipResponseMapper.membershipEntityToMembershipResponse(membership);
    }

    @Override
    public MembershipResponse enableById(Long membershipId) {
        MembershipEntity membership = membershipService.enableById(membershipId);
        return this.membershipResponseMapper.membershipEntityToMembershipResponse(membership);
    }

    @Override
    public List<MembershipResponse> getEnabledMembership() {
        List<MembershipEntity> memberships = this.membershipService.findByStatusId(Status.ENABLED.getCode());
        return this.membershipResponseMapper.membershipEntitiesToMembershipResponses(memberships);
    }

    @Override
    public List<MembershipResponse> getDisabledMembership() {
        List<MembershipEntity> memberships = this.membershipService.findByStatusId(Status.DISABLED.getCode());
        return this.membershipResponseMapper.membershipEntitiesToMembershipResponses(memberships);
    }
}
