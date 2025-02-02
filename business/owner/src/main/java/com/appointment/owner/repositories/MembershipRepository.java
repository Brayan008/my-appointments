package com.appointment.owner.repositories;

import com.appointment.owner.entities.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
}
