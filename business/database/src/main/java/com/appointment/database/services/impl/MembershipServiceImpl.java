package com.appointment.database.services.impl;

import com.appointment.commons.enums.Status;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.MembershipEntity;
import com.appointment.database.repositories.MembershipRepository;
import com.appointment.database.services.MembershipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MembershipServiceImpl implements MembershipService {
   private final MembershipRepository membershipRepository;

   @Override
   public List<MembershipEntity> getMemberships() {
      return membershipRepository.findAll();
   }

   @Override
   public MembershipEntity getMembershipById(Long membershipId) {
      return membershipRepository.findById(membershipId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "Membership not found with id " + membershipId, HttpStatus.NOT_FOUND));
   }

   @Override
   public MembershipEntity createMembership(MembershipEntity membership) {
      return membershipRepository.save(membership);
   }

   @Override
   public MembershipEntity updateMembership(MembershipEntity membership, Long membershipId) {
      MembershipEntity currentMembership = this.getMembershipById(membershipId);
      currentMembership.setDescription(membership.getDescription());
      currentMembership.setStatusId(membership.getStatusId());
      return this.createMembership(currentMembership);
   }

   @Override
   public MembershipEntity disableById(Long membershipId) {
      MembershipEntity currentMembership = this.getMembershipById(membershipId);
      currentMembership.setStatusId(Status.DISABLED.getCode());
      return this.createMembership(currentMembership);
   }

   @Override
   public MembershipEntity enableById(Long membershipId) {
      MembershipEntity currentMembership = this.getMembershipById(membershipId);
      currentMembership.setStatusId(Status.ENABLED.getCode());
      return this.createMembership(currentMembership);
   }

   @Override
   public List<MembershipEntity> findByStatusId(Long statusId) {
      return membershipRepository.findByStatusId(statusId);
   }
}
