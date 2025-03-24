package com.appointment.database.services.impl;

import com.appointment.commons.exceptions.BusinessException;
import com.appointment.database.entities.ClientAppointmentEntity;
import com.appointment.database.entities.ConfigEmployeeSchedule;
import com.appointment.database.repositories.ClientAppointmentsRepository;
import com.appointment.database.repositories.ConfigEmployeeScheduleRepository;
import com.appointment.database.services.ClientAppointmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ClientAppointmentsServiceImpl implements ClientAppointmentsService {
   private final ClientAppointmentsRepository clientAppointmentsRepository;
   private final ConfigEmployeeScheduleRepository configEmployeeScheduleRepository;
   private final MessageSource messageSource;

   @Override
   public ClientAppointmentEntity createClientAppointment(ClientAppointmentEntity clientAppointmentEntity) {
      Locale locale = LocaleContextHolder.getLocale();
      // Verify that the date does not exist and is not cancelled.
      if (clientAppointmentsRepository.existsActiveUserDate(clientAppointmentEntity.getUserAppointment())) {
         throw new BusinessException(
            String.valueOf(40901),
            messageSource.getMessage("error.40901.client-appointment", null, locale),
            messageSource.getMessage("error.40901.client-appointment.description", null, locale),
            HttpStatus.CONFLICT
         );
      }

      // Validate that the appointment is in the past.
      if (clientAppointmentEntity.getUserAppointment().isBefore(LocalDateTime.now())) {
         throw new BusinessException(
            String.valueOf(40902),
            messageSource.getMessage("error.40902.client-appointment", null, locale),
            messageSource.getMessage("error.40902.client-appointment.description", null, locale),
            HttpStatus.CONFLICT
         );
      }

      // Get employee settings for the relevant day.
      LocalDateTime userDateTime = clientAppointmentEntity.getUserAppointment();
      int dayOfWeek = userDateTime.getDayOfWeek().getValue(); // Monday=1, Sunday=7
      ConfigEmployeeSchedule schedule = configEmployeeScheduleRepository
         .findByStoreEmployeeEntityAndDayOfWeek(clientAppointmentEntity.getStoreEmployeeEntity(), dayOfWeek);

      if (schedule == null) {
         throw new BusinessException(
            String.valueOf(HttpStatus.NOT_FOUND.value()),
            messageSource.getMessage("error.404.config-employee-schedule", null, locale),
            messageSource.getMessage("error.404.config-employee-schedule.description", null, locale),
            HttpStatus.NOT_FOUND
         );
      }

      // Validate that the appointment time is within working hours.
      LocalTime appointmentTime = userDateTime.toLocalTime();
      if (appointmentTime.isBefore(schedule.getStartTime()) || appointmentTime.isAfter(schedule.getEndTime())) {
         throw new BusinessException(
            String.valueOf(40903),
            messageSource.getMessage("error.40903.client-appointment", null, locale),
            messageSource.getMessage(
               "error.40903.client-appointment.description",
               new Object[]{schedule.getStartTime(), schedule.getEndTime()},
               locale
            ),
            HttpStatus.CONFLICT
         );
      }

      // Validate that the appointment time is not in the break.
      if (!appointmentTime.isBefore(schedule.getStartTimeBreak()) && appointmentTime.isBefore(schedule.getEndTimeBreak())) {
         throw new BusinessException(
            String.valueOf(40904),
            messageSource.getMessage("error.40904.client-appointment", null, locale),
            messageSource.getMessage(
               "error.40904.client-appointment.description",
               new Object[]{schedule.getStartTimeBreak(), schedule.getEndTimeBreak()},
               locale
            ),
            HttpStatus.CONFLICT
         );
      }

      // Validate that the appointment time respects the intervals configured.
      long minutesDifference = Duration.between(schedule.getStartTime(), appointmentTime).toMinutes();
      if (minutesDifference % schedule.getIntervalInMinutes() != 0) {
         throw new BusinessException(
            HttpStatus.CONFLICT.name(),
            messageSource.getMessage("error.40905.client-appointment", null, locale),
            messageSource.getMessage(
               "error.40905.client-appointment.description",
               new Object[]{schedule.getIntervalInMinutes(), schedule.getStartTime()},
               locale
            ),
            HttpStatus.CONFLICT
         );
      }

      // Validate limit of appointments per day
      int clientAppointmentsCount = clientAppointmentsRepository.countAppointmentsByClientAndDay(clientAppointmentEntity.getUserAppointment(), clientAppointmentEntity.getClientEntity().getUserId());
      if (clientAppointmentsCount >= schedule.getAppointmentsPerClient()) {
         throw new BusinessException(HttpStatus.CONFLICT.name(),
            messageSource.getMessage("error.40906.client-appointment", null, locale),
            messageSource.getMessage("error.40906.client-appointment.description", null, locale),
            HttpStatus.CONFLICT);
      }

      //Set status of date configured
      clientAppointmentEntity.setStatusAppointmentId(schedule.getDefaultStatusAppointmentEntity().getStatusAppointmentId());
      return clientAppointmentsRepository.save(clientAppointmentEntity);
   }
}
