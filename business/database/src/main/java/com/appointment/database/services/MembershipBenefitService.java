package com.appointment.database.services;

import com.appointment.database.entities.MembershipBenefit;

import java.util.List;

public interface MembershipBenefitService {
    List<MembershipBenefit> getBenefits();

    MembershipBenefit getBenefitById(Long benefitId);

    MembershipBenefit createBenefit(MembershipBenefit benefit);

    MembershipBenefit updateBenefit(MembershipBenefit benefit, Long benefitId);

}
