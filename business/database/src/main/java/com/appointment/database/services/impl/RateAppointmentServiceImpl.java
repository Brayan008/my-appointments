package com.appointment.database.services.impl;

import com.appointment.commons.exceptions.BusinessException;
import com.appointment.database.entities.RateAppointmentEntity;
import com.appointment.database.repositories.RateAppointmentRepository;
import com.appointment.database.services.RateAppointmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class RateAppointmentServiceImpl implements RateAppointmentService {

   private final RateAppointmentRepository rateAppointmentRepository;
   private final MessageSource messageSource;

   @Override
   public RateAppointmentEntity addRateAppointment(RateAppointmentEntity rateAppointment) {
      Locale locale = LocaleContextHolder.getLocale();
      RateAppointmentEntity rateAppointmentEntity = this.rateAppointmentRepository.findByClientAppointment(rateAppointment.getClientAppointment());

      if (rateAppointmentEntity != null) {
         throw new BusinessException(
            String.valueOf(40901),
            messageSource.getMessage("error.40901.rate-appointment", null, locale),
            messageSource.getMessage("error.40901.rate-appointment.description", null, locale),
            HttpStatus.CONFLICT
         );
      }

      return this.rateAppointmentRepository.save(rateAppointment);
   }

   @Override
   public RateAppointmentEntity updateRateAppointment(String emailClient, RateAppointmentEntity rateAppointment) {
      Locale locale = LocaleContextHolder.getLocale();
      RateAppointmentEntity rateAppointmentEntity = this.rateAppointmentRepository.findById(rateAppointment.getRateAppointmentId())
         .orElseThrow(() -> new BusinessException(
            String.valueOf(404),
            messageSource.getMessage("error.404.rate-appointment", null, locale),
            "",
            HttpStatus.NOT_FOUND
         ));

      if(!Objects.equals(emailClient, rateAppointmentEntity.getClientAppointment().getClient().getEmail())){
         throw new BusinessException(
            String.valueOf(40001),
            messageSource.getMessage("error.40001.rate-appointment", null, locale),
            "",
            HttpStatus.BAD_REQUEST
         );
      }

      rateAppointmentEntity.setComment(rateAppointment.getComment());
      rateAppointmentEntity.setRate(rateAppointment.getRate());

      return this.rateAppointmentRepository.save(rateAppointmentEntity);
   }
}
