package com.appointment.database.repositories;

import com.appointment.database.entities.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
   List<MembershipEntity> findByStatusId(Long statusId);
}
