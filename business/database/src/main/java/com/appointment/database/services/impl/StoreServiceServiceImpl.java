package com.appointment.database.services.impl;

import com.appointment.commons.enums.StatusEnum;
import com.appointment.commons.exceptions.BusinessException;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.StoreServiceEntity;
import com.appointment.database.repositories.StoreServiceRepository;
import com.appointment.database.services.StoreServiceService;
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
public class StoreServiceServiceImpl implements StoreServiceService {
   private final StoreServiceRepository storeServiceRepository;
   private final MessageSource messageSource;

   @Override
   public List<StoreServiceEntity> getStoreServices() {
      return storeServiceRepository.findAll();
   }

   @Override
   public StoreServiceEntity getStoreServiceById(Long storeServiceId) {
      Locale locale = LocaleContextHolder.getLocale();
      StoreServiceEntity service = storeServiceRepository.findById(storeServiceId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            messageSource.getMessage("error.404.store-services", null, locale), HttpStatus.NOT_FOUND));
      if(service.getStatusId().equals(StatusEnum.DISABLED.getCode()))
         throw new BusinessException(HttpStatus.CONFLICT.name(), messageSource.getMessage("error.4091.store-services", null, locale), "", HttpStatus.CONFLICT);
      return service;
   }

   @Override
   public StoreServiceEntity createStoreService(StoreServiceEntity service) {
      return storeServiceRepository.save(service);
   }

   @Override
   public StoreServiceEntity updateStoreService(StoreServiceEntity service, Long storeServiceId) {
      StoreServiceEntity currentService = this.getStoreServiceById(storeServiceId);
      currentService.setName(service.getName());
      currentService.setStatusId(service.getStatusId());
      currentService.setPrice(service.getPrice());
      currentService.setStatusId(service.getStatusId());
      return this.createStoreService(currentService);
   }

   @Override
   public StoreServiceEntity disableById(Long storeServiceId) {
      StoreServiceEntity currentService = this.getStoreServiceById(storeServiceId);
      currentService.setStatusId(StatusEnum.DISABLED.getCode());
      return this.createStoreService(currentService);
   }

   @Override
   public StoreServiceEntity enableById(Long storeServiceId) {
      StoreServiceEntity currentService = this.getStoreServiceById(storeServiceId);
      currentService.setStatusId(StatusEnum.ENABLED.getCode());
      return this.createStoreService(currentService);
   }

   @Override
   public List<StoreServiceEntity> findByStatusId(Long statusId) {
      return storeServiceRepository.findByStatusId(statusId);
   }
}
