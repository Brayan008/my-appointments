package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.response.MembershipResponse;
import com.appointment.owner.entities.MembershipEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembershipResponseMapper {

    MembershipResponse membershipEntityToMembershipResponse(MembershipEntity membershipEntity);

    MembershipEntity membershipResponseToMembershipEntity(MembershipResponse membershipResponse);

    List<MembershipResponse> membershipEntitiesToMembershipResponses(List<MembershipEntity> membershipEntities);
}
