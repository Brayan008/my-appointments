package com.appointment.database.services.impl;

import com.appointment.commons.enums.Status;
import com.appointment.commons.exceptions.BusinessException;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.ServiceEntity;
import com.appointment.database.repositories.ServiceRepository;
import com.appointment.database.services.ServiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {
   private final ServiceRepository serviceRepository;
   private final MessageSource messageSource;

   @Override
   public List<ServiceEntity> getServices() {
      return serviceRepository.findAll();
   }

   @Override
   public ServiceEntity getServiceById(Long serviceId) {
      Locale locale = LocaleContextHolder.getLocale();
      ServiceEntity service = serviceRepository.findById(serviceId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            messageSource.getMessage("error.404.store-services", null, locale), HttpStatus.NOT_FOUND));
      if(service.getStatusId().equals(Status.DISABLED.getCode()))
         throw new BusinessException(HttpStatus.CONFLICT.name(), messageSource.getMessage("error.4091.store-services", null, locale), "", HttpStatus.CONFLICT);
      return service;
   }

   @Override
   public ServiceEntity createService(ServiceEntity service) {
      return serviceRepository.save(service);
   }

   @Override
   public ServiceEntity updateService(ServiceEntity service, Long serviceId) {
      ServiceEntity currentService = this.getServiceById(serviceId);
      currentService.setName(service.getName());
      currentService.setStatusId(service.getStatusId());
      currentService.setPrice(service.getPrice());
      currentService.setStatusId(service.getStatusId());
      return this.createService(currentService);
   }

   @Override
   public ServiceEntity disableById(Long serviceId) {
      ServiceEntity currentService = this.getServiceById(serviceId);
      currentService.setStatusId(Status.DISABLED.getCode());
      return this.createService(currentService);
   }

   @Override
   public ServiceEntity enableById(Long serviceId) {
      ServiceEntity currentService = this.getServiceById(serviceId);
      currentService.setStatusId(Status.ENABLED.getCode());
      return this.createService(currentService);
   }

   @Override
   public List<ServiceEntity> findByStatusId(Long statusId) {
      return serviceRepository.findByStatusId(statusId);
   }
}
