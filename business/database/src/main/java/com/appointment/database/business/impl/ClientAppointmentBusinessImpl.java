package com.appointment.database.business.impl;

import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import com.appointment.commons.enums.StatusAppointmentsEnum;
import com.appointment.commons.exceptions.BusinessException;
import com.appointment.database.business.ClientAppointmentBusiness;
import com.appointment.database.entities.*;
import com.appointment.database.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClientAppointmentBusinessImpl implements ClientAppointmentBusiness {
   private final UserService userService;
   private final ClientAppointmentsService clientAppointmentsService;
   private final StoreServiceService serviceService;
   private final StoreEmployeeService storeEmployeeService;
   private final RateAppointmentService rateAppointmentService;
   private final MessageSource messageSource;

   @Override
   public ClientAppointmentEntity createClientAppointment(ClientDBAppointmentRequest clientDBAppointmentRequest) {
      UserEntity client = this.userService.getUserByEmail(clientDBAppointmentRequest.getClientEmail());
      StoreServiceEntity service = this.serviceService.getServiceById(clientDBAppointmentRequest.getServiceId());
      StoreEmployeeEntity storeEmployee = this.storeEmployeeService.getStoreEmployeeById(clientDBAppointmentRequest.getStoreEmployeeId());

      ClientAppointmentEntity clientAppointmentEntity = new ClientAppointmentEntity();
      clientAppointmentEntity.setStoreEmployee(storeEmployee);
      clientAppointmentEntity.setClient(client);
      clientAppointmentEntity.setUserAppointment(clientDBAppointmentRequest.getUserAppointment());
      clientAppointmentEntity.setService(service);
      clientAppointmentEntity.setTotalPaid(service.getPrice());
      return this.clientAppointmentsService.createClientAppointment(clientAppointmentEntity);
   }

   @Override
   public Page<ClientAppointmentEntity> findClientAppointment(String email, int page, int size) {
      UserEntity client = this.userService.getUserByEmail(email);
      return this.clientAppointmentsService.findClientAppointment(client, page, size);
   }

   @Override
   public GenericResponse addRateAppointment(Long idClientAppointment, RateAppointmentRequest rateAppointmentRequest) {
      Locale locale = LocaleContextHolder.getLocale();
      ClientAppointmentEntity clientAppointment = this.clientAppointmentsService.findClientAppointmentByIdAndStatus(idClientAppointment, StatusAppointmentsEnum.COMPLETED);
      if (clientAppointment == null) {
         throw new BusinessException("40401",
            messageSource.getMessage("error.40401.rate-appointment", null, locale),
            "",
            HttpStatus.NOT_FOUND);
      }

      if(!Objects.equals(clientAppointment.getClient().getEmail(), rateAppointmentRequest.getEmailClient())){
         throw new BusinessException("40001",
            messageSource.getMessage("error.40001.rate-appointment", null, locale),
            "",
            HttpStatus.BAD_REQUEST);
      }

      RateAppointmentEntity rateAppointmentEntity = new RateAppointmentEntity();
      rateAppointmentEntity.setClientAppointment(clientAppointment);
      rateAppointmentEntity.setRate(rateAppointmentRequest.getRate());
      rateAppointmentEntity.setComment(rateAppointmentRequest.getComment());

      rateAppointmentEntity = this.rateAppointmentService.addRateAppointment(rateAppointmentEntity);
      return GenericResponse.builder()
         .title(messageSource.getMessage("message.2001.rate-appointment",
            new Object[]{rateAppointmentEntity.getClientAppointment().getStoreEmployee().getUser().getEmail(), rateAppointmentEntity.getClientAppointment().getService().getName(), rateAppointmentEntity.getClientAppointment().getStoreEmployee().getStore().getName() },
            locale))
         .build();
   }

   @Override
   public GenericResponse updateRateAppointment(Long idRateAppointment, RateAppointmentRequest rateAppointmentRequest) {
      Locale locale = LocaleContextHolder.getLocale();
      RateAppointmentEntity rateAppointmentEntity = new RateAppointmentEntity();
      rateAppointmentEntity.setRateAppointmentId(idRateAppointment);
      rateAppointmentEntity.setComment(rateAppointmentRequest.getComment());
      rateAppointmentEntity.setRate(rateAppointmentRequest.getRate());
      this.rateAppointmentService.updateRateAppointment(rateAppointmentRequest.getEmailClient(), rateAppointmentEntity);
      return GenericResponse.builder()
         .title(messageSource.getMessage("message.2003.rate-appointment",
            null,
            locale))
         .build();
   }

   @Override
   public List<ClientAppointmentEntity> getAppointmentsByEmployeeAndDateWithoutStatus(Long storeEmployeeId, LocalDate date, Long excludedStatusId) {
      StoreEmployeeEntity storeEmployeeEntity = this.storeEmployeeService.getStoreEmployeeById(storeEmployeeId);
      return this.clientAppointmentsService.getAppointmentsByStoreEmployeeEntityAndDateWithoutStatus(storeEmployeeEntity, date, excludedStatusId);
   }


}
