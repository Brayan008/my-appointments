package com.appointment.database.services.impl;

import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.StatusEntity;
import com.appointment.database.repositories.StatusRepository;
import com.appointment.database.services.StatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {
   private final StatusRepository statusRepository;

   @Override
   public List<StatusEntity> getStatus() {
      return statusRepository.findAll();
   }

   @Override
   public StatusEntity getStatusById(Long statusId) {
      return statusRepository.findById(statusId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "status not found with id " + statusId, HttpStatus.NOT_FOUND));
   }

   @Override
   public StatusEntity createStatus(StatusEntity status) {
      return this.statusRepository.save(status);
   }

   @Override
   public StatusEntity updateStatus(StatusEntity status, Long statusId) {
      StatusEntity currentStatus = this.getStatusById(statusId);
      currentStatus.setName(status.getName());
      return this.createStatus(currentStatus);
   }
}
