package com.appointment.database.services;

import com.appointment.database.entities.UserEntity;

import java.util.List;

public interface UserService {
   List<UserEntity> getUsers();

   UserEntity getUserById(Long userId);

   UserEntity createUser(UserEntity user);

   UserEntity updateUser(UserEntity user, Long userId);

   UserEntity disableById(Long userId);

   UserEntity enableById(Long userId);

   List<UserEntity> findByStatusId(Long statusId);

   UserEntity getUserByEmail(String email);

}
