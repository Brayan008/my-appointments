package com.appointment.database.business;

import com.appointment.database.entities.ConfigEmployeeSchedule;

public interface ConfigEmployeeScheduleBusiness {
   ConfigEmployeeSchedule findByStoreEmployeeIdAndDayOfWeek(Long storeEmployeeId, Integer dayOfWeek);
}
