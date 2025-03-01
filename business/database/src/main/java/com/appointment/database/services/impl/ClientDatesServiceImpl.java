package com.appointment.database.services.impl;

import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.ClientDateEntity;
import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.repositories.ClientDatesRepository;
import com.appointment.database.repositories.StoreEmployeeRepository;
import com.appointment.database.services.ClientDatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientDatesServiceImpl implements ClientDatesService {

    private final ClientDatesRepository clientDatesRepository;
    private final StoreEmployeeRepository storeEmployeeRepository;

    @Override
    public ClientDateEntity createClientDate(ClientDateEntity clientDateEntity) {

        StoreEmployeeEntity storeEmployeeEntity = storeEmployeeRepository.findByStoreIdAndUserId(clientDateEntity.getServiceEntity().getStoreId(), clientDateEntity.getEmployeeEntity().getUserId())
            .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
                "The employee at the store not found", HttpStatus.NOT_FOUND));



        return clientDatesRepository.save(clientDateEntity);
    }
}
