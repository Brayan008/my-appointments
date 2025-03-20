package com.appointment.database.services.impl;

import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.MembershipBenefit;
import com.appointment.database.repositories.MembershipBenefitRepository;
import com.appointment.database.services.MembershipBenefitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MembershipBenefitServiceImpl implements MembershipBenefitService {
   private final MembershipBenefitRepository membershipBenefitRepository;

   @Override
   public List<MembershipBenefit> getBenefits() {
      return membershipBenefitRepository.findAll();
   }

   @Override
   public MembershipBenefit getBenefitById(Long benefitId) {
      return membershipBenefitRepository.findById(benefitId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "benefit not found with id " + benefitId, HttpStatus.NOT_FOUND));
   }

   @Override
   public MembershipBenefit createBenefit(MembershipBenefit benefit) {
      return membershipBenefitRepository.save(benefit);
   }

   @Override
   public MembershipBenefit updateBenefit(MembershipBenefit benefit, Long benefitId) {
      MembershipBenefit currentBenefit = this.getBenefitById(benefitId);
      currentBenefit.setBenefit(benefit.getBenefit());
      currentBenefit.setDescription(benefit.getDescription());
      currentBenefit.setMembershipId(benefit.getMembershipId());
      return this.createBenefit(currentBenefit);
   }
}
