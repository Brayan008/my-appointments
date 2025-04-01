package com.appointment.database.services;

import com.appointment.database.entities.ConfigEmployeeSchedule;
import com.appointment.database.entities.StoreEmployeeEntity;

public interface ConfigEmployeeScheduleService {

   ConfigEmployeeSchedule findByStoreEmployeeEntityAndDayOfWeek(StoreEmployeeEntity storeEmployeeEntity, Integer dayOfWeek);

}
