package com.appointment.owner.repositories;

import com.appointment.owner.entities.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
    List<MembershipEntity> findByStatusId(Long statusId);
}
