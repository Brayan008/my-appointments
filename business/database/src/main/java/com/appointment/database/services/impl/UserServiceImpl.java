package com.appointment.database.services.impl;

import com.appointment.commons.enums.Status;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.UserEntity;
import com.appointment.database.repositories.UserRepository;
import com.appointment.database.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
        currentUser.setStatusId(Status.DISABLED.getCode());
        return this.createUser(currentUser);
    }

    @Override
    public UserEntity enableById(Long userId) {
        UserEntity currentUser = this.getUserById(userId);
        currentUser.setStatusId(Status.ENABLED.getCode());
        return this.createUser(currentUser);
    }

    @Override
    public List<UserEntity> findByStatusId(Long statusId) {
        return this.userRepository.findByStatusId(statusId);
    }
}
