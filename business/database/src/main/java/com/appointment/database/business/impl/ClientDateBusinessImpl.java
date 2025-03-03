package com.appointment.database.business.impl;

import com.appointment.commons.dtos.request.ClientDateRequest;
import com.appointment.database.business.ClientDatesBusiness;
import com.appointment.database.entities.ClientDateEntity;
import com.appointment.database.entities.ServiceEntity;
import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.entities.UserEntity;
import com.appointment.database.services.ClientDatesService;
import com.appointment.database.services.ServiceService;
import com.appointment.database.services.StoreEmployeeService;
import com.appointment.database.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientDateBusinessImpl implements ClientDatesBusiness {

    private final UserService userService;
    private final ClientDatesService clientDatesService;
    private final ServiceService serviceService;
    private final StoreEmployeeService storeEmployeeService;


    @Override
    public ClientDateEntity createClientDate(ClientDateRequest clientDateRequest) {
        UserEntity client = this.userService.getUserByEmail(clientDateRequest.getClientEmail(), "client");
        ServiceEntity service = this.serviceService.getServiceById(clientDateRequest.getServiceId());
        StoreEmployeeEntity storeEmployee = this.storeEmployeeService.getStoreEmployeeById(clientDateRequest.getStoreEmployeeId());

        ClientDateEntity clientDateEntity = new ClientDateEntity();
        clientDateEntity.setStoreEmployeeEntity(storeEmployee);
        clientDateEntity.setClientEntity(client);
        clientDateEntity.setUserDate(clientDateRequest.getUserDate());
        clientDateEntity.setServiceEntity(service);
        clientDateEntity.setTotalPaid(clientDateRequest.getTotalPaid());
        return this.clientDatesService.createClientDate(clientDateEntity);
    }
}
