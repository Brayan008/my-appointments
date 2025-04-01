package com.appointment.database.services.impl;

import com.appointment.database.entities.ConfigEmployeeSchedule;
import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.repositories.ConfigEmployeeScheduleRepository;
import com.appointment.database.services.ConfigEmployeeScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigEmployeeScheduleServiceImpl implements ConfigEmployeeScheduleService {

   private final ConfigEmployeeScheduleRepository configEmployeeScheduleRepository;
   @Override
   public ConfigEmployeeSchedule findByStoreEmployeeEntityAndDayOfWeek(StoreEmployeeEntity storeEmployeeEntity, Integer dayOfWeek) {
      return this.configEmployeeScheduleRepository.findByStoreEmployeeAndDayOfWeek(storeEmployeeEntity, dayOfWeek);
   }
}
