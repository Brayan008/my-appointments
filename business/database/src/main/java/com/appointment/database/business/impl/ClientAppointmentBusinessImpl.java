package com.appointment.database.business.impl;

import com.appointment.commons.dtos.request.ClientDBAppointmentRequest;
import com.appointment.database.business.ClientAppointmentBusiness;
import com.appointment.database.entities.ClientAppointmentEntity;
import com.appointment.database.entities.ServiceEntity;
import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.entities.UserEntity;
import com.appointment.database.services.ClientAppointmentsService;
import com.appointment.database.services.ServiceService;
import com.appointment.database.services.StoreEmployeeService;
import com.appointment.database.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientAppointmentBusinessImpl implements ClientAppointmentBusiness {
   private final UserService userService;
   private final ClientAppointmentsService clientAppointmentsService;
   private final ServiceService serviceService;
   private final StoreEmployeeService storeEmployeeService;

   @Override
   public ClientAppointmentEntity createClientAppointment(ClientDBAppointmentRequest clientDBAppointmentRequest) {
      UserEntity client = this.userService.getUserByEmail(clientDBAppointmentRequest.getClientEmail());
      ServiceEntity service = this.serviceService.getServiceById(clientDBAppointmentRequest.getServiceId());
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


}
