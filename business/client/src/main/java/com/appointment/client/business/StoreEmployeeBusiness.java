package com.appointment.client.business;

import com.appointment.commons.dtos.response.EmployeeAppointmentsAvailabilityResponse;
import reactor.core.publisher.Flux;

public interface StoreEmployeeBusiness {
   Flux<EmployeeAppointmentsAvailabilityResponse> getEmployeeAvailability(Long storeEmployeeId);
}
