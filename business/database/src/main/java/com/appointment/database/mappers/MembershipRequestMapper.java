package com.appointment.database.mappers;

import com.appointment.commons.dtos.request.MembershipRequest;
import com.appointment.database.entities.MembershipEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembershipRequestMapper {

    MembershipEntity membershipRequestToMembershipEntity(MembershipRequest membershipRequest);

    MembershipRequest membershipEntityToMembershipRequest(MembershipEntity membershipEntity);

    List<MembershipEntity> membershipRequestsToMembershipEntities(List<MembershipRequest> membershipRequests);
}
