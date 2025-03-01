package com.appointment.database.services;

import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.entities.UserEntity;

import java.util.List;

public interface StoreEmployeeService {
    List<StoreEmployeeEntity> getEmployees();

    StoreEmployeeEntity getEmployeeById(Long employeeId);

    StoreEmployeeEntity createEmployee(StoreEmployeeEntity employee);

    StoreEmployeeEntity updateEmployee(StoreEmployeeEntity currentEmployee, Long employeeId);

    StoreEmployeeEntity disableById(Long employeeId);

    StoreEmployeeEntity enableById(Long employeeId);

    List<StoreEmployeeEntity> findByStatusId(Long statusId);

    List<UserEntity> getEmployeesAssociated(Long storeId);
}
