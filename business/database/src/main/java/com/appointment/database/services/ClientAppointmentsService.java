package com.appointment.database.services;

import com.appointment.commons.enums.StatusAppointmentsEnum;
import com.appointment.database.entities.ClientAppointmentEntity;
import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.entities.UserEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ClientAppointmentsService {
   ClientAppointmentEntity createClientAppointment(ClientAppointmentEntity clientAppointmentEntity);
   Page<ClientAppointmentEntity> findClientAppointment(UserEntity userEntity, int page, int size);
   ClientAppointmentEntity findClientAppointmentByIdAndStatus(Long idClientAppointment, StatusAppointmentsEnum status);
   List<ClientAppointmentEntity> getAppointmentsByStoreEmployeeEntityAndDateWithoutStatus(StoreEmployeeEntity storeEmployee, LocalDate date, Long excludedStatusId);
}
