package com.appointment.database.business.impl;

import com.appointment.database.business.ConfigEmployeeScheduleBusiness;
import com.appointment.database.entities.ConfigEmployeeSchedule;
import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.services.ConfigEmployeeScheduleService;
import com.appointment.database.services.StoreEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigEmployeeScheduleBusinessImpl implements ConfigEmployeeScheduleBusiness {

   private final StoreEmployeeService storeEmployeeService;
   private final ConfigEmployeeScheduleService configEmployeeScheduleService;

   @Override
   public ConfigEmployeeSchedule findByStoreEmployeeIdAndDayOfWeek(Long storeEmployeeId, Integer dayOfWeek) {
      StoreEmployeeEntity storeEmployeeEntity = this.storeEmployeeService.getStoreEmployeeById(storeEmployeeId);
      return this.configEmployeeScheduleService.findByStoreEmployeeEntityAndDayOfWeek(storeEmployeeEntity, dayOfWeek);
   }
}
