package com.appointment.database.repositories;

import com.appointment.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
   List<UserEntity> findByStatusId(Long statusId);
   Optional<UserEntity> findByEmail(String email);
}
