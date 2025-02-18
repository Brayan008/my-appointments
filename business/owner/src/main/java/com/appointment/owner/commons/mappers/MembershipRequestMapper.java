package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.request.MembershipRequest;
import com.appointment.owner.entities.MembershipEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembershipRequestMapper {

    MembershipEntity membershipRequestToMembershipEntity(MembershipRequest membershipRequest);

    MembershipRequest membershipEntityToMembershipRequest(MembershipEntity membershipEntity);

    List<MembershipEntity> membershipRequestsToMembershipEntities(List<MembershipRequest> membershipRequests);
}
