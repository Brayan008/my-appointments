package com.appointment.database.repositories;

import com.appointment.database.entities.MembershipBenefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipBenefitRepository extends JpaRepository<MembershipBenefit, Long> {
}
