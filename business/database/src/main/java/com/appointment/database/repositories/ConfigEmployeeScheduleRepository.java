package com.appointment.database.repositories;

import com.appointment.database.entities.ConfigEmployeeSchedule;
import com.appointment.database.entities.StoreEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigEmployeeScheduleRepository extends JpaRepository<ConfigEmployeeSchedule, Long> {
   ConfigEmployeeSchedule findByStoreEmployeeEntityAndDayOfWeek(StoreEmployeeEntity storeEmployeeEntity, Integer dayOfWeek);
}
