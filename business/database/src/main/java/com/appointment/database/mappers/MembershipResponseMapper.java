package com.appointment.database.mappers;

import com.appointment.commons.dtos.response.MembershipResponse;
import com.appointment.database.entities.MembershipEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembershipResponseMapper {

    MembershipResponse membershipEntityToMembershipResponse(MembershipEntity membershipEntity);

    MembershipEntity membershipResponseToMembershipEntity(MembershipResponse membershipResponse);

    List<MembershipResponse> membershipEntitiesToMembershipResponses(List<MembershipEntity> membershipEntities);
}
