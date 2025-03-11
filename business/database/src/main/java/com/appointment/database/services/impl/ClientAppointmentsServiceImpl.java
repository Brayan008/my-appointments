package com.appointment.database.services.impl;

import com.appointment.commons.exceptions.BusinessException;
import com.appointment.database.entities.ClientAppointmentEntity;
import com.appointment.database.entities.ConfigEmployeeSchedule;
import com.appointment.database.repositories.ClientAppointmentsRepository;
import com.appointment.database.repositories.ConfigEmployeeScheduleRepository;
import com.appointment.database.services.ClientAppointmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ClientAppointmentsServiceImpl implements ClientAppointmentsService {

    private final ClientAppointmentsRepository clientAppointmentsRepository;
    private final ConfigEmployeeScheduleRepository configEmployeeScheduleRepository;

    @Override
    public ClientAppointmentEntity createClientAppointment(ClientAppointmentEntity clientAppointmentEntity) {
        // Verify that the date does not exist and is not cancelled.
        if (clientAppointmentsRepository.existsActiveUserDate(clientAppointmentEntity.getUserAppointment())) {
            throw new BusinessException(
                HttpStatus.CONFLICT.name(),
                "That date is currently occupied",
                "That date exists and is not canceled.",
                HttpStatus.CONFLICT
            );
        }

        // Validate that the appointment is in the future.
        if (clientAppointmentEntity.getUserAppointment().isBefore(LocalDateTime.now())) {
            throw new BusinessException(
                HttpStatus.CONFLICT.name(),
                "Appointment date is in the past",
                "The selected appointment date must be in the future.",
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
                HttpStatus.CONFLICT.name(),
                "Employee not available on the selected day",
                "No schedule configuration found for the employee on the selected day.",
                HttpStatus.CONFLICT
            );
        }

        // Validate that the appointment time is within working hours.
        LocalTime appointmentTime = userDateTime.toLocalTime();
        if (appointmentTime.isBefore(schedule.getStartTime()) || appointmentTime.isAfter(schedule.getEndTime())) {
            throw new BusinessException(
                HttpStatus.CONFLICT.name(),
                "Appointment time is outside working hours",
                "The selected appointment time must be between " + schedule.getStartTime() + " and " + schedule.getEndTime() + ".",
                HttpStatus.CONFLICT
            );
        }

        // Validate that the appointment time is not in the break.
        if (!appointmentTime.isBefore(schedule.getStartTimeBreak()) && appointmentTime.isBefore(schedule.getEndTimeBreak())) {
            throw new BusinessException(
                HttpStatus.CONFLICT.name(),
                "Appointment time falls within break period",
                "The selected appointment time falls within the employee's break period (" + schedule.getStartTimeBreak() + " - " + schedule.getEndTimeBreak() + ").",
                HttpStatus.CONFLICT
            );
        }

        // Validate that the appointment time respects the intervals configured.
        long minutesDifference = Duration.between(schedule.getStartTime(), appointmentTime).toMinutes();
        if (minutesDifference % schedule.getIntervalInMinutes() != 0) {
            throw new BusinessException(
                HttpStatus.CONFLICT.name(),
                "Appointment time does not match schedule intervals",
                "The appointment time must be scheduled in increments of " + schedule.getIntervalInMinutes() + " minutes starting at " + schedule.getStartTime() + ".",
                HttpStatus.CONFLICT
            );
        }

        // Validate limit of appointments per day
        int clientAppointmentsCount = clientAppointmentsRepository.countAppointmentsByClientAndDay(clientAppointmentEntity.getUserAppointment(), clientAppointmentEntity.getClientEntity().getUserId());
        if (clientAppointmentsCount >= schedule.getAppointmentsPerDay()) {
            throw new BusinessException(HttpStatus.CONFLICT.name(),
                "Daily appointment limit reached",
                "The client has reached the maximum number of appointments for this day.",
                HttpStatus.CONFLICT);
        }


        //Set status of date configured
        clientAppointmentEntity.setStatusAppointmentId(schedule.getDefaultStatusAppointmentEntity().getStatusAppointmentId());
        return clientAppointmentsRepository.save(clientAppointmentEntity);
    }
}
