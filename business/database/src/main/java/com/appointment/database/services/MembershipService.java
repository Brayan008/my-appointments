package com.appointment.database.services;

import com.appointment.database.entities.MembershipEntity;

import java.util.List;

public interface MembershipService {
   List<MembershipEntity> getMemberships();

   MembershipEntity getMembershipById(Long membershipId);

   MembershipEntity createMembership(MembershipEntity membership);

   MembershipEntity updateMembership(MembershipEntity membership, Long membershipId);

   MembershipEntity disableById(Long membershipId);

   MembershipEntity enableById(Long membershipId);

   List<MembershipEntity> findByStatusId(Long statusId);
}
