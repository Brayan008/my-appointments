package com.appointment.database.services.impl;

import com.appointment.commons.constants.StatusConstants;
import com.appointment.commons.enums.StatusEnum;
import com.appointment.commons.exceptions.BusinessException;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.UserEntity;
import com.appointment.database.repositories.UserRepository;
import com.appointment.database.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;
   private final MessageSource messageSource;

   @Override
   public List<UserEntity> getUsers() {
      return userRepository.findAll();
   }

   @Override
   public UserEntity getUserById(Long userId) {
      return userRepository.findById(userId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "user not found with id " + userId, HttpStatus.NOT_FOUND));
   }

   @Override
   public UserEntity createUser(UserEntity user) {
      return this.userRepository.save(user);
   }

   @Override
   public UserEntity updateUser(UserEntity user, Long userId) {
      UserEntity currentUser = this.getUserById(userId);
      currentUser.setEmail(user.getEmail());
      currentUser.setStatusId(user.getStatusId());
      currentUser.setRoleId(user.getRoleId());
      return this.createUser(currentUser);
   }

   @Override
   public UserEntity disableById(Long userId) {
      UserEntity currentUser = this.getUserById(userId);
      currentUser.setStatusId(StatusEnum.DISABLED.getCode());
      return this.createUser(currentUser);
   }

   @Override
   public UserEntity enableById(Long userId) {
      UserEntity currentUser = this.getUserById(userId);
      currentUser.setStatusId(StatusEnum.ENABLED.getCode());
      return this.createUser(currentUser);
   }

   @Override
   public List<UserEntity> findByStatusId(Long statusId) {
      return this.userRepository.findByStatusId(statusId);
   }

   @Override
   public UserEntity getUserByEmail(String email) {
      Locale locale = LocaleContextHolder.getLocale();
      UserEntity user = userRepository.findByEmail(email)
         .orElseThrow(() -> new BusinessException(String.valueOf(HttpStatus.NOT_FOUND.value()),
            messageSource.getMessage("error.404.user", null, locale), "", HttpStatus.NOT_FOUND));

      if(user.getStatusId().equals(StatusConstants.ID_STATUS_DISABLED))
         throw new BusinessException(String.valueOf(4091),
            messageSource.getMessage("error.4091.user", null, locale), "", HttpStatus.CONFLICT);

      return user;
   }
}
