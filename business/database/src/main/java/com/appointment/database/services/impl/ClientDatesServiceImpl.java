package com.appointment.database.services.impl;

import com.appointment.database.entities.ClientDateEntity;
import com.appointment.database.repositories.ClientDatesRepository;
import com.appointment.database.services.ClientDatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientDatesServiceImpl implements ClientDatesService {

    private final ClientDatesRepository clientDatesRepository;

    @Override
    public ClientDateEntity createClientDate(ClientDateEntity clientDateEntity) {


        return clientDatesRepository.save(clientDateEntity);
    }
}
