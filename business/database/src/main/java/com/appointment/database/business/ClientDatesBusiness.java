package com.appointment.database.business;

import com.appointment.commons.dtos.request.ClientDateRequest;
import com.appointment.database.entities.ClientDateEntity;

public interface ClientDatesBusiness {

    ClientDateEntity createClientDate(ClientDateRequest clientDateRequest);


}
