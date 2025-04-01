package com.appointment.database.business;

import com.appointment.commons.dtos.GenericResponse;
import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.commons.dtos.request.RateAppointmentRequest;
import com.appointment.database.entities.ClientAppointmentEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ClientAppointmentBusiness {
   ClientAppointmentEntity createClientAppointment(ClientDBAppointmentRequest clientDBAppointmentRequest);
   Page<ClientAppointmentEntity> findClientAppointment(String email, int page, int size);
   GenericResponse addRateAppointment(Long idClientAppointment, RateAppointmentRequest rateAppointmentRequest);
   GenericResponse updateRateAppointment(Long idRateAppointment, RateAppointmentRequest rateAppointmentRequest);
   List<ClientAppointmentEntity> getAppointmentsByEmployeeAndDateWithoutStatus(Long storeEmployeeId, LocalDate date, Long excludedStatusId);

}
