package com.appointment.database.repositories;

import com.appointment.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByStatusId(Long statusId);
}
