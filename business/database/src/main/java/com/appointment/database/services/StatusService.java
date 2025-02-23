package com.appointment.database.services;

import com.appointment.database.entities.StatusEntity;

import java.util.List;

public interface StatusService {
    List<StatusEntity> getStatus();

    StatusEntity getStatusById(Long statusId);

    StatusEntity createStatus(StatusEntity status);

    StatusEntity updateStatus(StatusEntity status, Long statusId);
}
